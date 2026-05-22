package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant
import net.madscientists.geep.core.model.TaskStatus

@Entity(
    tableName = "planned_tasks",
    foreignKeys = [
        ForeignKey(
            entity = FutureEventEntity::class,
            parentColumns = ["recordId"],
            childColumns = ["futureEventId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("futureEventId")]
)
data class PlannedTaskEntity(
    @PrimaryKey
    val futureEventId: Long,
    val status: TaskStatus?,
    val reminderDate: Instant,
    val dueDate: Instant,
    val content: String
)
