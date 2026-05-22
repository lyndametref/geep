package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant
import net.madscientists.geep.core.model.DelayStatus

@Entity(
    tableName = "waiting_delays",
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
data class WaitingDelayEntity(
    @PrimaryKey
    val futureEventId: Long,
    val status: DelayStatus?,
    val title: String,
    val delayElapsedAt: Instant,
    val content: String?
)
