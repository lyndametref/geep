package net.madscientists.geep.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.madscientists.geep.core.database.GeepDatabase
import net.madscientists.geep.core.database.entity.*
import net.madscientists.geep.core.model.DelayStatus
import net.madscientists.geep.core.model.PredictionStatus
import net.madscientists.geep.core.model.Sex
import net.madscientists.geep.core.model.TaskStatus
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RunWith(AndroidJUnit4::class)
class RecordDaoTest {

    private lateinit var db: GeepDatabase
    private lateinit var recordDao: RecordDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GeepDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        recordDao = db.recordDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertRecord_WithValidData_ReturnsSuccess() = runBlocking {
        val record = RecordEntity(
            id = 1L,
            timestamp = Instant.now(),
            recordType = "OBSERVATION",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        val readRecord = recordDao.getRecordById(1L)
        assertNotNull(readRecord)
        assertEquals(record, readRecord)
    }

    @Test
    fun insertRecord_WithDuplicateId_ReplacesExistingRecord() = runBlocking {
        val now = Instant.now()
        val record1 = RecordEntity(
            id = 1L,
            timestamp = now,
            recordType = "OBSERVATION",
            individualId = null,
            sourceRecordId = null
        )
        val record2 = RecordEntity(
            id = 1L,
            timestamp = now.plusSeconds(1),
            recordType = "INTERVENTION",
            individualId = 2L,
            sourceRecordId = null
        )
        
        // Insert the referenced IndividualEntity first to satisfy the foreign key constraint
        val individual = IndividualEntity(
            id = 2L,
            name = "Test Individual",
            earTagId = "TAG002",
            birthDate = LocalDate.now(),
            deathDate = null,
            sex = Sex.MALE,
            colorPattern = null,
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null,
            notes = null
        )
        db.individualDao().insertIndividual(individual)
        
        recordDao.insertRecord(record1)
        recordDao.insertRecord(record2)
        
        val readRecord = recordDao.getRecordById(1L)
        assertNotNull(readRecord)
        assertEquals("INTERVENTION", readRecord?.recordType)
        assertEquals(2L, readRecord?.individualId)
    }

    @Test
    fun updateRecord_WithValidData_UpdatesRecord() = runBlocking {
        val now = Instant.now()
        val record = RecordEntity(
            id = 1L,
            timestamp = now,
            recordType = "OBSERVATION",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        val updatedRecord = record.copy(recordType = "INTERVENTION")
        recordDao.updateRecord(updatedRecord)
        
        val readRecord = recordDao.getRecordById(1L)
        assertNotNull(readRecord)
        assertEquals("INTERVENTION", readRecord?.recordType)
    }

    @Test
    fun deleteRecord_WithExistingRecord_DeletesRecord() = runBlocking {
        val record = RecordEntity(
            id = 1L,
            timestamp = Instant.now(),
            recordType = "OBSERVATION",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        recordDao.deleteRecord(record)
        val readRecord = recordDao.getRecordById(1L)
        assertNull(readRecord)
    }

    @Test
    fun getAllRecords_WithMultipleRecords_ReturnsAllRecords() = runBlocking {
        val now = Instant.now()
        val record1 = RecordEntity(1L, now, "OBSERVATION", null, null)
        val record2 = RecordEntity(2L, now, "INTERVENTION", null, null)
        
        recordDao.insertRecord(record1)
        recordDao.insertRecord(record2)
        
        val records = recordDao.getAllRecords().first()
        assertEquals(2, records.size)
    }

    @Test
    fun insertObservation_WithValidData_InsertsObservation() = runBlocking {
        val now = Instant.now()
        // First insert the parent RecordEntity
        val record = RecordEntity(
            id = 1L,
            timestamp = now,
            recordType = "OBSERVATION",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        // Then insert the observation
        val observation = ObservationEntity(
            recordId = 1L,
            observedAt = now,
            content = "{\"type\":\"WEIGHT\",\"value\":\"75.5 kg\"}"
        )
        recordDao.insertObservation(observation)
        
        val readObservation = recordDao.getObservationByRecordId(1L)
        assertNotNull(readObservation)
        assertEquals(observation, readObservation)
    }

    @Test
    fun insertIntervention_WithValidData_InsertsIntervention() = runBlocking {
        val now = Instant.now()
        // First insert the parent RecordEntity
        val record = RecordEntity(
            id = 1L,
            timestamp = now,
            recordType = "INTERVENTION",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        // Then insert the intervention
        val intervention = InterventionEntity(
            recordId = 1L,
            performedAt = now,
            content = "{\"type\":\"TREATMENT\"}"
        )
        recordDao.insertIntervention(intervention)
        
        val readIntervention = recordDao.getInterventionByRecordId(1L)
        assertNotNull(readIntervention)
        assertEquals(intervention, readIntervention)
    }

    @Test
    fun insertFutureEvent_WithValidData_InsertsFutureEvent() = runBlocking {
        // First insert the parent RecordEntity
        val record = RecordEntity(
            id = 1L,
            timestamp = Instant.now(),
            recordType = "FUTURE_EVENT",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        // Then insert the future event
        val futureEvent = FutureEventEntity(recordId = 1L)
        recordDao.insertFutureEvent(futureEvent)
        
        val readFutureEvent = recordDao.getFutureEventByRecordId(1L)
        assertNotNull(readFutureEvent)
        assertEquals(futureEvent, readFutureEvent)
    }

    @Test
    fun insertPredictedEvent_WithValidData_InsertsPredictedEvent() = runBlocking {
        val now = Instant.now()
        // First insert the parent RecordEntity
        val record = RecordEntity(
            id = 1L,
            timestamp = now,
            recordType = "FUTURE_EVENT",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        // Then insert the future event
        val futureEvent = FutureEventEntity(recordId = 1L)
        recordDao.insertFutureEvent(futureEvent)
        
        // Then insert the predicted event
        val predictedEvent = PredictedEventEntity(
            futureEventId = 1L,
            status = PredictionStatus.PENDING,
            earliestDate = now.plusSeconds(140L * 86400L),
            latestDate = now.plusSeconds(150L * 86400L),
            content = null
        )
        recordDao.insertPredictedEvent(predictedEvent)
        
        val readPredictedEvent = recordDao.getPredictedEventByFutureEventId(1L)
        assertNotNull(readPredictedEvent)
        assertEquals(predictedEvent, readPredictedEvent)
    }

    @Test
    fun insertPlannedTask_WithValidData_InsertsPlannedTask() = runBlocking {
        val now = Instant.now()
        // First insert the parent RecordEntity
        val record = RecordEntity(
            id = 1L,
            timestamp = now,
            recordType = "FUTURE_EVENT",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        // Then insert the future event
        val futureEvent = FutureEventEntity(recordId = 1L)
        recordDao.insertFutureEvent(futureEvent)
        
        // Then insert the planned task
        val plannedTask = PlannedTaskEntity(
            futureEventId = 1L,
            status = TaskStatus.PENDING,
            reminderDate = now.plusSeconds(90L * 86400L),
            dueDate = now.plusSeconds(95L * 86400L),
            content = "{\"title\":\"Weaning\"}"
        )
        recordDao.insertPlannedTask(plannedTask)
        
        val readPlannedTask = recordDao.getPlannedTaskByFutureEventId(1L)
        assertNotNull(readPlannedTask)
        assertEquals(plannedTask, readPlannedTask)
    }

    @Test
    fun insertWaitingDelay_WithValidData_InsertsWaitingDelay() = runBlocking {
        val now = Instant.now()
        // First insert the parent RecordEntity
        val record = RecordEntity(
            id = 1L,
            timestamp = now,
            recordType = "FUTURE_EVENT",
            individualId = null,
            sourceRecordId = null
        )
        recordDao.insertRecord(record)
        
        // Then insert the future event
        val futureEvent = FutureEventEntity(recordId = 1L)
        recordDao.insertFutureEvent(futureEvent)
        
        // Then insert the waiting delay
        val waitingDelay = WaitingDelayEntity(
            futureEventId = 1L,
            status = DelayStatus.WAITING,
            title = "Meat withdrawal",
            delayElapsedAt = now.plusSeconds(30L * 86400L),
            content = null
        )
        recordDao.insertWaitingDelay(waitingDelay)
        
        val readWaitingDelay = recordDao.getWaitingDelayByFutureEventId(1L)
        assertNotNull(readWaitingDelay)
        assertEquals(waitingDelay, readWaitingDelay)
    }

    @Test
    fun getRecordsByIndividualId_WithNoRecords_ReturnsEmptyList() = runBlocking {
        val records = recordDao.getRecordsByIndividualId(999L).first()
        assertTrue(records.isEmpty())
    }

    @Test
    fun getObservationByRecordId_WithNonExistentRecordId_ReturnsNull() = runBlocking {
        val observation = recordDao.getObservationByRecordId(999L)
        assertNull(observation)
    }

    @Test
    fun getInterventionByRecordId_WithNonExistentRecordId_ReturnsNull() = runBlocking {
        val intervention = recordDao.getInterventionByRecordId(999L)
        assertNull(intervention)
    }

    @Test
    fun getFutureEventByRecordId_WithNonExistentRecordId_ReturnsNull() = runBlocking {
        val futureEvent = recordDao.getFutureEventByRecordId(999L)
        assertNull(futureEvent)
    }

    @Test
    fun getPredictedEventByFutureEventId_WithNonExistentFutureEventId_ReturnsNull() = runBlocking {
        val predictedEvent = recordDao.getPredictedEventByFutureEventId(999L)
        assertNull(predictedEvent)
    }

    @Test
    fun getPlannedTaskByFutureEventId_WithNonExistentFutureEventId_ReturnsNull() = runBlocking {
        val plannedTask = recordDao.getPlannedTaskByFutureEventId(999L)
        assertNull(plannedTask)
    }

    @Test
    fun getWaitingDelayByFutureEventId_WithNonExistentFutureEventId_ReturnsNull() = runBlocking {
        val waitingDelay = recordDao.getWaitingDelayByFutureEventId(999L)
        assertNull(waitingDelay)
    }
}
