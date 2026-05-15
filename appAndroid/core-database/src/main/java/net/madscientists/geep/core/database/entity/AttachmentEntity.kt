package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "attachments",
    foreignKeys = [
        ForeignKey(
            entity = RecordEntity::class,
            parentColumns = ["id"],
            childColumns = ["recordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("recordId")]
)
data class AttachmentEntity(
    @PrimaryKey
    val id: String,
    val recordId: String,
    val attachmentType: String,
    val uri: String,
    val label: String?,
    val capturedAt: LocalDateTime?
)
