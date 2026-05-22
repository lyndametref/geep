package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant
import net.madscientists.geep.core.model.PredictionStatus

@Entity(
    tableName = "predicted_events",
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
data class PredictedEventEntity(
    @PrimaryKey
    val futureEventId: Long,
    val status: PredictionStatus?,
    val earliestDate: Instant,
    val latestDate: Instant,
    val content: String?
)
