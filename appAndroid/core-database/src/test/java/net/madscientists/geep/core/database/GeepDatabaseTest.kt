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
import net.madscientists.geep.core.database.entity.IndividualEntity
import net.madscientists.geep.core.database.entity.IndividualRecordCrossRef
import net.madscientists.geep.core.database.entity.RecordEntity
import net.madscientists.geep.core.model.Sex
import net.madscientists.geep.core.model.FutureEventStatus
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * Core CRUD and data integrity tests for GeepDatabase.
 * Validates that all entities can be created, modified, and deleted correctly,
 * and that baseline constraints (foreign keys, cascades) are enforced.
 */
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
        val id = "sheep-123"
        val sheep = IndividualEntity(
            id = id,
            name = "Bessie",
            bdtaNumber = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            sireId = "ram-1",
            damId = "ewe-1"
        )

        // CREATE
        individualDao.insertIndividual(sheep)
        val read = individualDao.getIndividualById(id)
        assertNotNull(read)
        assertEquals(sheep, read)

        // MODIFY
        val updatedSheep = sheep.copy(
            name = "Bessie Updated",
            living = false
        )
        individualDao.updateIndividual(updatedSheep)
        val readUpdated = individualDao.getIndividualById(id)
        assertEquals("Bessie Updated", readUpdated?.name)
        assertEquals(false, readUpdated?.living)

        // DELETE
        individualDao.deleteIndividual(updatedSheep)
        assertNull(individualDao.getIndividualById(id))
    }

    @Test
    fun record_PolymorphicCRUD_EnsuresDataIntegrity() = runBlocking {
        val now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        
        // OBSERVATION
        val obs = RecordEntity(
            id = "obs-1",
            timestamp = now,
            type = "OBSERVATION",
            observationType = "WEIGHT",
            content = "75.5 kg"
        )
        recordDao.insertRecord(obs)
        assertEquals(obs, recordDao.getRecordById("obs-1"))

        // PLANNED TASK
        val task = RecordEntity(
            id = "task-1",
            timestamp = now.plusMinutes(1),
            type = "PLANNED_TASK",
            status = FutureEventStatus.PLANNED,
            title = "Shearing"
        )
        recordDao.insertRecord(task)
        val readTask = recordDao.getRecordById("task-1")
        assertEquals("Shearing", readTask?.title)
        assertEquals(FutureEventStatus.PLANNED, readTask?.status)

        // DELETE
        recordDao.deleteRecord(obs)
        assertNull(recordDao.getRecordById("obs-1"))
        assertEquals(1, recordDao.getAllRecords().first().size)
    }

    @Test
    fun attachment_FullCRUD_WithCascadingDelete() = runBlocking {
        val recordId = "rec-1"
        recordDao.insertRecord(RecordEntity(id = recordId, timestamp = LocalDateTime.now(), type = "OBSERVATION"))

        val attachment = AttachmentEntity(
            id = "att-1",
            recordId = recordId,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
        )

        // CREATE
        attachmentDao.insertAttachment(attachment)
        assertEquals(attachment, attachmentDao.getAttachmentById("att-1"))

        // CASCADE DELETE
        recordDao.deleteRecord(recordDao.getRecordById(recordId)!!)
        assertNull("Attachment should be deleted when parent record is removed", attachmentDao.getAttachmentById("att-1"))
    }

    @Test
    fun batchRecord_Association_Persistence() = runBlocking {
        val s1 = "s1"
        val s2 = "s2"
        individualDao.insertIndividual(createSheep(s1))
        individualDao.insertIndividual(createSheep(s2))

        val record = RecordEntity(id = "batch-rec", timestamp = LocalDateTime.now(), type = "INTERVENTION")
        
        // ASSOCIATE
        recordDao.insertBatchRecord(record, listOf(s1, s2))

        // Verify associations exist
        val associations = recordDao.getAllRecordAssociations().first()
        assertEquals(2, associations.size)
        assertTrue(associations.contains(IndividualRecordCrossRef(s1, "batch-rec")))
        assertTrue(associations.contains(IndividualRecordCrossRef(s2, "batch-rec")))

        // DELETE INDIVIDUAL -> Association should be cleaned up by cascade
        individualDao.deleteIndividual(individualDao.getIndividualById(s1)!!)
        val associationsAfter = recordDao.getAllRecordAssociations().first()
        assertEquals(1, associationsAfter.size)
        assertEquals(s2, associationsAfter[0].individualId)
    }

    private fun createSheep(id: String) = IndividualEntity(
        id = id, name = "Sheep $id", bdtaNumber = null, birthDate = LocalDate.now(),
        deathDate = null, sex = Sex.FEMALE, colorPattern = null, living = true, stillborn = false,
        sireId = null, damId = null
    )
}
