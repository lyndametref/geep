package net.madscientists.geep.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.madscientists.geep.core.database.dao.AttachmentDao
import net.madscientists.geep.core.database.dao.IndividualDao
import net.madscientists.geep.core.database.dao.RecordDao
import net.madscientists.geep.core.database.entity.AttachmentEntity
import net.madscientists.geep.core.database.entity.FutureEventEntity
import net.madscientists.geep.core.database.entity.IndividualEntity
import net.madscientists.geep.core.database.entity.InterventionEntity
import net.madscientists.geep.core.database.entity.ObservationEntity
import net.madscientists.geep.core.database.entity.PlannedTaskEntity
import net.madscientists.geep.core.database.entity.PredictedEventEntity
import net.madscientists.geep.core.database.entity.RecordEntity
import net.madscientists.geep.core.database.entity.WaitingDelayEntity
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
class GeepDatabaseTest {

    private lateinit var db: GeepDatabase
    private lateinit var individualDao: IndividualDao
    private lateinit var recordDao: RecordDao
    private lateinit var attachmentDao: AttachmentDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GeepDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        individualDao = db.individualDao()
        recordDao = db.recordDao()
        attachmentDao = db.attachmentDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun individual_CRUD_VerifiesAllFields() = runBlocking {
        val id = 100L
        val sheep = IndividualEntity(
            id = id,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )

        individualDao.insertIndividual(sheep)
        val read = individualDao.getIndividualById(id)
        assertNotNull(read)
        assertEquals(sheep, read)

        val updatedSheep = sheep.copy(
            name = "Bessie Updated",
            living = false
        )
        individualDao.updateIndividual(updatedSheep)
        val readUpdated = individualDao.getIndividualById(id)
        assertEquals("Bessie Updated", readUpdated?.name)
        assertEquals(false, readUpdated?.living)

        individualDao.deleteIndividual(updatedSheep)
        assertNull(individualDao.getIndividualById(id))
    }

    @Test
    fun observation_CRUD_EnsuresDataIntegrity() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val sheepId = 201L
        individualDao.insertIndividual(createSheep(sheepId))

        recordDao.insertObservationRecord(
            recordId = 1L,
            timestamp = now,
            individualId = sheepId,
            sourceRecordId = null,
            observation = ObservationEntity(
                recordId = 1L,
                observedAt = now,
                content = "{\"type\":\"WEIGHT\",\"value\":\"75.5 kg\"}"
            )
        )

        val readRecord = recordDao.getRecordById(1L)
        assertNotNull(readRecord)
        assertEquals(now, readRecord?.timestamp)
        assertEquals("OBSERVATION", readRecord?.recordType)
        assertEquals(sheepId, readRecord?.individualId)

        val readObs = recordDao.getObservationByRecordId(1L)
        assertNotNull(readObs)
        assertEquals("{\"type\":\"WEIGHT\",\"value\":\"75.5 kg\"}", readObs?.content)

        recordDao.deleteRecord(readRecord!!)
        assertNull(recordDao.getRecordById(1L))
        assertNull(recordDao.getObservationByRecordId(1L))
    }

    @Test
    fun intervention_CRUD_EnsuresDataIntegrity() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val sheepId = 202L
        individualDao.insertIndividual(createSheep(sheepId))

        recordDao.insertInterventionRecord(
            recordId = 2L,
            timestamp = now,
            individualId = sheepId,
            sourceRecordId = null,
            intervention = InterventionEntity(
                recordId = 2L,
                performedAt = now,
                content = "{\"type\":\"TREATMENT\"}"
            )
        )

        val readRecord = recordDao.getRecordById(2L)
        assertEquals("INTERVENTION", readRecord?.recordType)
        assertEquals(sheepId, readRecord?.individualId)

        val readInt = recordDao.getInterventionByRecordId(2L)
        assertNotNull(readInt)
        assertEquals("{\"type\":\"TREATMENT\"}", readInt?.content)
    }

    @Test
    fun predictedEvent_CRUD_EnsuresDataIntegrity() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val sheepId = 204L
        individualDao.insertIndividual(createSheep(sheepId))

        recordDao.insertPredictedEventRecord(
            recordId = 3L,
            timestamp = now,
            individualId = sheepId,
            sourceRecordId = null,
            futureEvent = FutureEventEntity(recordId = 3L),
            predictedEvent = PredictedEventEntity(
                futureEventId = 3L,
                status = PredictionStatus.PENDING,
                earliestDate = now.plusSeconds(140L * 86400L),
                latestDate = now.plusSeconds(150L * 86400L),
                content = null
            )
        )

        val readRecord = recordDao.getRecordById(3L)
        assertEquals("FUTURE_EVENT", readRecord?.recordType)

        val readFe = recordDao.getFutureEventByRecordId(3L)
        assertNotNull(readFe)

        val readPe = recordDao.getPredictedEventByFutureEventId(3L)
        assertNotNull(readPe)
        assertEquals(PredictionStatus.PENDING, readPe?.status)
    }

    @Test
    fun plannedTask_CRUD_EnsuresDataIntegrity() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val sheepId = 205L
        individualDao.insertIndividual(createSheep(sheepId))

        recordDao.insertPlannedTaskRecord(
            recordId = 4L,
            timestamp = now,
            individualId = sheepId,
            sourceRecordId = null,
            futureEvent = FutureEventEntity(recordId = 4L),
            plannedTask = PlannedTaskEntity(
                futureEventId = 4L,
                status = TaskStatus.PENDING,
                reminderDate = now.plusSeconds(90L * 86400L),
                dueDate = now.plusSeconds(95L * 86400L),
                content = "{\"title\":\"Weaning\"}"
            )
        )

        val readRecord = recordDao.getRecordById(4L)
        assertEquals("FUTURE_EVENT", readRecord?.recordType)

        val readPt = recordDao.getPlannedTaskByFutureEventId(4L)
        assertNotNull(readPt)
        assertEquals(TaskStatus.PENDING, readPt?.status)
        assertEquals("{\"title\":\"Weaning\"}", readPt?.content)
    }

    @Test
    fun waitingDelay_CRUD_EnsuresDataIntegrity() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val sheepId = 206L
        individualDao.insertIndividual(createSheep(sheepId))

        recordDao.insertWaitingDelayRecord(
            recordId = 5L,
            timestamp = now,
            individualId = sheepId,
            sourceRecordId = null,
            futureEvent = FutureEventEntity(recordId = 5L),
            waitingDelay = WaitingDelayEntity(
                futureEventId = 5L,
                status = DelayStatus.WAITING,
                title = "Meat withdrawal",
                delayElapsedAt = now.plusSeconds(30L * 86400L),
                content = null
            )
        )

        val readRecord = recordDao.getRecordById(5L)
        assertEquals("FUTURE_EVENT", readRecord?.recordType)

        val readWd = recordDao.getWaitingDelayByFutureEventId(5L)
        assertNotNull(readWd)
        assertEquals("Meat withdrawal", readWd?.title)
        assertEquals(DelayStatus.WAITING, readWd?.status)
    }

    @Test
    fun attachment_FullCRUD_WithCascadingDelete() = runBlocking {
        val recordId = 10L
        recordDao.insertRecord(
            RecordEntity(
                id = recordId,
                timestamp = Instant.now(),
                recordType = "OBSERVATION",
                individualId = null,
                sourceRecordId = null
            )
        )

        val attachment = AttachmentEntity(
            id = 100L,
            recordId = recordId,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        )

        attachmentDao.insertAttachment(attachment)
        assertEquals(attachment, attachmentDao.getAttachmentById(100L))

        recordDao.deleteRecord(recordDao.getRecordById(recordId)!!)
        assertNull("Attachment should be deleted when parent record is removed", attachmentDao.getAttachmentById(100L))
    }

    @Test
    fun deleteIndividual_NullsOutRecordIndividualId() = runBlocking {
        val sheepId = 301L
        individualDao.insertIndividual(createSheep(sheepId))

        recordDao.insertObservationRecord(
            recordId = 20L,
            timestamp = Instant.now(),
            individualId = sheepId,
            sourceRecordId = null,
            observation = ObservationEntity(
                recordId = 20L,
                observedAt = Instant.now(),
                content = "{\"type\":\"CHECKUP\"}"
            )
        )

        val recordBefore = recordDao.getRecordById(20L)
        assertEquals(sheepId, recordBefore?.individualId)

        individualDao.deleteIndividual(individualDao.getIndividualById(sheepId)!!)

        val recordAfter = recordDao.getRecordById(20L)
        assertNotNull("Record should still exist after individual is deleted", recordAfter)
        assertNull("individualId should be null after individual is deleted", recordAfter?.individualId)
    }

    @Test
    fun getRecordsByIndividualId_ReturnsOnlyMatchingRecords() = runBlocking {
        val sheepId = 401L
        val otherId = 402L
        individualDao.insertIndividual(createSheep(sheepId))
        individualDao.insertIndividual(createSheep(otherId))

        val now = Instant.now()
        recordDao.insertObservationRecord(
            recordId = 30L, timestamp = now, individualId = sheepId, sourceRecordId = null,
            observation = ObservationEntity(recordId = 30L, observedAt = now, content = "{}")
        )
        recordDao.insertObservationRecord(
            recordId = 31L, timestamp = now, individualId = otherId, sourceRecordId = null,
            observation = ObservationEntity(recordId = 31L, observedAt = now, content = "{}")
        )

        val records = recordDao.getRecordsByIndividualId(sheepId).first()
        assertEquals(1, records.size)
        assertEquals(30L, records[0].id)
    }

    private fun createSheep(id: Long) = IndividualEntity(
        id = id, name = "Sheep $id", earTagId = null, birthDate = LocalDate.now(),
        deathDate = null, sex = Sex.FEMALE, colorPattern = null, living = true, stillborn = false,
        belongsToFlock = true, sireId = null, damId = null
    )
}
