package net.madscientists.geep.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import net.madscientists.geep.core.database.entity.AttachmentEntity

/**
 * Core CRUD operations for Attachment metadata.
 */
@Dao
interface AttachmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: AttachmentEntity)

    @Update
    suspend fun updateAttachment(attachment: AttachmentEntity)

    @Delete
    suspend fun deleteAttachment(attachment: AttachmentEntity)

    @Query("SELECT * FROM attachments WHERE id = :id")
    suspend fun getAttachmentById(id: Long): AttachmentEntity?

    @Query("SELECT * FROM attachments")
    fun getAllAttachments(): Flow<List<AttachmentEntity>>
}
