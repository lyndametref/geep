package net.madscientists.geep.core.model

import java.time.LocalDateTime

sealed class Record {
    abstract val id: String
    abstract val timestamp: LocalDateTime
}

data class Observation(
    override val id: String,
    override val timestamp: LocalDateTime,
    val type: String,
    val content: String,
    val individualIds: List<String>
) : Record()

data class Intervention(
    override val id: String,
    override val timestamp: LocalDateTime,
    val type: String,
    val individualIds: List<String>
) : Record()

sealed class FutureEvent : Record() {
    abstract val status: FutureEventStatus
    abstract val sourceRecordId: String?
}

enum class FutureEventStatus {
    PLANNED,
    PREDICTED,
    WAITING,
    REALIZED,
    ABORTED
}

data class PredictedEvent(
    override val id: String,
    override val timestamp: LocalDateTime,
    override val status: FutureEventStatus,
    override val sourceRecordId: String?,
    val earliestDate: LocalDateTime,
    val latestDate: LocalDateTime,
    val type: String
) : FutureEvent()

data class PlannedTask(
    override val id: String,
    override val timestamp: LocalDateTime,
    override val status: FutureEventStatus,
    override val sourceRecordId: String?,
    val title: String,
    val reminderDate: LocalDateTime,
    val dueDate: LocalDateTime
) : FutureEvent()

data class WaitingDelay(
    override val id: String,
    override val timestamp: LocalDateTime,
    override val status: FutureEventStatus,
    override val sourceRecordId: String?,
    val title: String,
    val delayElapsedAt: LocalDateTime
) : FutureEvent()
