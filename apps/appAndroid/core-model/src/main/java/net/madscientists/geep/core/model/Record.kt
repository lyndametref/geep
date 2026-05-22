package net.madscientists.geep.core.model

import java.time.Instant

sealed class Record {
    abstract val id: String
    abstract val timestamp: Instant
    abstract val individualId: String?
    abstract val sourceRecordId: String?
}

data class Observation(
    override val id: String,
    override val timestamp: Instant,
    override val individualId: String?,
    override val sourceRecordId: String?,
    val observedAt: Instant,
    val content: String
) : Record()

data class Intervention(
    override val id: String,
    override val timestamp: Instant,
    override val individualId: String?,
    override val sourceRecordId: String?,
    val performedAt: Instant,
    val content: String
) : Record()

sealed class FutureEvent : Record()

data class PredictedEvent(
    override val id: String,
    override val timestamp: Instant,
    override val individualId: String?,
    override val sourceRecordId: String?,
    val status: PredictionStatus?,
    val earliestDate: Instant,
    val latestDate: Instant,
    val content: String?
) : FutureEvent()

data class PlannedTask(
    override val id: String,
    override val timestamp: Instant,
    override val individualId: String?,
    override val sourceRecordId: String?,
    val status: TaskStatus?,
    val reminderDate: Instant,
    val dueDate: Instant,
    val content: String
) : FutureEvent()

data class WaitingDelay(
    override val id: String,
    override val timestamp: Instant,
    override val individualId: String?,
    override val sourceRecordId: String?,
    val status: DelayStatus?,
    val title: String,
    val delayElapsedAt: Instant,
    val content: String?
) : FutureEvent()
