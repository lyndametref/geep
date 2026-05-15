package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.madscientists.geep.core.model.FutureEventStatus
import java.time.LocalDateTime

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey
    val id: String,
    val timestamp: LocalDateTime,
    val type: String, // OBSERVATION, INTERVENTION, PREDICTED_EVENT, PLANNED_TASK, WAITING_DELAY
    
    // Observation specific
    val observationType: String? = null,
    val content: String? = null,
    
    // Intervention specific
    val interventionType: String? = null,
    
    // FutureEvent specific
    val status: FutureEventStatus? = null,
    val sourceRecordId: String? = null,
    
    // PredictedEvent specific
    val earliestDate: LocalDateTime? = null,
    val latestDate: LocalDateTime? = null,
    
    // PlannedTask specific
    val title: String? = null,
    val reminderDate: LocalDateTime? = null,
    val dueDate: LocalDateTime? = null,
    
    // WaitingDelay specific
    val delayElapsedAt: LocalDateTime? = null
)
