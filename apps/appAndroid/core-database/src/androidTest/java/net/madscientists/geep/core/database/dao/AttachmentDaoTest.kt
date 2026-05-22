package net.madscientists.geep.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.madscientists.geep.core.database.GeepDatabase
import net.madscientists.geep.core.database.entity.AttachmentEntity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
import java.time.temporal.ChronoUnit

@RunWith(AndroidJUnit4::class)
class AttachmentDaoTest {

    private lateinit var db: GeepDatabase
    private lateinit var attachmentDao: AttachmentDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GeepDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        attachmentDao = db.attachmentDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAttachment_WithValidData_InsertsAttachment() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val attachment = AttachmentEntity(
            id = 1L,
            recordId = 10L,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = now
        )
        attachmentDao.insertAttachment(attachment)
        
        val readAttachment = attachmentDao.getAttachmentById(1L)
        assertNotNull(readAttachment)
        assertEquals(attachment, readAttachment)
    }

    @Test
    fun insertAttachment_WithDuplicateId_ReplacesExistingAttachment() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val attachment1 = AttachmentEntity(
            id = 1L,
            recordId = 10L,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = now
        )
        val attachment2 = AttachmentEntity(
            id = 1L,
            recordId = 11L,
            attachmentType = "VIDEO",
            uri = "content://media/2",
            label = "After",
            capturedAt = now.plusSeconds(1)
        )
        
        attachmentDao.insertAttachment(attachment1)
        attachmentDao.insertAttachment(attachment2)
        
        val readAttachment = attachmentDao.getAttachmentById(1L)
        assertNotNull(readAttachment)
        assertEquals("VIDEO", readAttachment?.attachmentType)
        assertEquals("After", readAttachment?.label)
    }

    @Test
    fun updateAttachment_WithValidData_UpdatesAttachment() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val attachment = AttachmentEntity(
            id = 1L,
            recordId = 10L,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = now
        )
        attachmentDao.insertAttachment(attachment)
        
        val updatedAttachment = attachment.copy(label = "Updated Label")
        attachmentDao.updateAttachment(updatedAttachment)
        
        val readAttachment = attachmentDao.getAttachmentById(1L)
        assertNotNull(readAttachment)
        assertEquals("Updated Label", readAttachment?.label)
    }

    @Test
    fun deleteAttachment_WithExistingAttachment_DeletesAttachment() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val attachment = AttachmentEntity(
            id = 1L,
            recordId = 10L,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = now
        )
        attachmentDao.insertAttachment(attachment)
        
        attachmentDao.deleteAttachment(attachment)
        val readAttachment = attachmentDao.getAttachmentById(1L)
        assertNull(readAttachment)
    }

    @Test
    fun getAllAttachments_WithMultipleAttachments_ReturnsAllAttachments() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val attachment1 = AttachmentEntity(1L, 10L, "PHOTO", "content://media/1", "Before", now)
        val attachment2 = AttachmentEntity(2L, 11L, "VIDEO", "content://media/2", "After", now)
        
        attachmentDao.insertAttachment(attachment1)
        attachmentDao.insertAttachment(attachment2)
        
        val attachments = attachmentDao.getAllAttachments().first()
        assertEquals(2, attachments.size)
    }

    @Test
    fun getAttachmentById_WithNonExistentId_ReturnsNull() = runBlocking {
        val attachment = attachmentDao.getAttachmentById(999L)
        assertNull(attachment)
    }

    @Test
    fun updateAttachment_WithNonExistentAttachment_DoesNotThrow() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val attachment = AttachmentEntity(
            id = 999L,
            recordId = 10L,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = now
        )
        // Should not throw an exception
        attachmentDao.updateAttachment(attachment)
    }

    @Test
    fun deleteAttachment_WithNonExistentAttachment_DoesNotThrow() = runBlocking {
        val now = Instant.now().truncatedTo(ChronoUnit.MILLIS)
        val attachment = AttachmentEntity(
            id = 999L,
            recordId = 10L,
            attachmentType = "PHOTO",
            uri = "content://media/1",
            label = "Before",
            capturedAt = now
        )
        // Should not throw an exception
        attachmentDao.deleteAttachment(attachment)
    }
}