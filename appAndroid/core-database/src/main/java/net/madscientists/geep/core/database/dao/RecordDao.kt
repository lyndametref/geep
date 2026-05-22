package net.madscientists.geep.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.Instant
import net.madscientists.geep.core.database.entity.FutureEventEntity
import net.madscientists.geep.core.database.entity.InterventionEntity
import net.madscientists.geep.core.database.entity.ObservationEntity
import net.madscientists.geep.core.database.entity.PlannedTaskEntity
import net.madscientists.geep.core.database.entity.PredictedEventEntity
import net.madscientists.geep.core.database.entity.RecordEntity
import net.madscientists.geep.core.database.entity.WaitingDelayEntity

/**
 * Core CRUD operations for Records and their subtype tables.
 */
@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordEntity)

    @Update
    suspend fun updateRecord(record: RecordEntity)

    @Delete
    suspend fun deleteRecord(record: RecordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObservation(observation: ObservationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntervention(intervention: InterventionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFutureEvent(futureEvent: FutureEventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPredictedEvent(predictedEvent: PredictedEventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlannedTask(plannedTask: PlannedTaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaitingDelay(waitingDelay: WaitingDelayEntity)

    /**
     * Helper factories for each record type — ensures recordType is always correct.
     */
    companion object {
        private const val RT_OBSERVATION = "OBSERVATION"
        private const val RT_INTERVENTION = "INTERVENTION"
        private const val RT_FUTURE_EVENT = "FUTURE_EVENT"
    }

    @Transaction
    suspend fun insertObservationRecord(
        recordId: Long,
        timestamp: Instant,
        individualId: Long?,
        sourceRecordId: Long?,
        observation: ObservationEntity
    ) {
        val record = RecordEntity(
            id = recordId,
            timestamp = timestamp,
            recordType = RT_OBSERVATION,
            individualId = individualId,
            sourceRecordId = sourceRecordId
        )
        insertRecord(record)
        insertObservation(observation)
    }

    @Transaction
    suspend fun insertInterventionRecord(
        recordId: Long,
        timestamp: Instant,
        individualId: Long?,
        sourceRecordId: Long?,
        intervention: InterventionEntity
    ) {
        val record = RecordEntity(
            id = recordId,
            timestamp = timestamp,
            recordType = RT_INTERVENTION,
            individualId = individualId,
            sourceRecordId = sourceRecordId
        )
        insertRecord(record)
        insertIntervention(intervention)
    }

    @Transaction
    suspend fun insertPredictedEventRecord(
        recordId: Long,
        timestamp: Instant,
        individualId: Long?,
        sourceRecordId: Long?,
        futureEvent: FutureEventEntity,
        predictedEvent: PredictedEventEntity
    ) {
        val record = RecordEntity(
            id = recordId,
            timestamp = timestamp,
            recordType = RT_FUTURE_EVENT,
            individualId = individualId,
            sourceRecordId = sourceRecordId
        )
        insertRecord(record)
        insertFutureEvent(futureEvent)
        insertPredictedEvent(predictedEvent)
    }

    @Transaction
    suspend fun insertPlannedTaskRecord(
        recordId: Long,
        timestamp: Instant,
        individualId: Long?,
        sourceRecordId: Long?,
        futureEvent: FutureEventEntity,
        plannedTask: PlannedTaskEntity
    ) {
        val record = RecordEntity(
            id = recordId,
            timestamp = timestamp,
            recordType = RT_FUTURE_EVENT,
            individualId = individualId,
            sourceRecordId = sourceRecordId
        )
        insertRecord(record)
        insertFutureEvent(futureEvent)
        insertPlannedTask(plannedTask)
    }

    @Transaction
    suspend fun insertWaitingDelayRecord(
        recordId: Long,
        timestamp: Instant,
        individualId: Long?,
        sourceRecordId: Long?,
        futureEvent: FutureEventEntity,
        waitingDelay: WaitingDelayEntity
    ) {
        val record = RecordEntity(
            id = recordId,
            timestamp = timestamp,
            recordType = RT_FUTURE_EVENT,
            individualId = individualId,
            sourceRecordId = sourceRecordId
        )
        insertRecord(record)
        insertFutureEvent(futureEvent)
        insertWaitingDelay(waitingDelay)
    }

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getRecordById(id: Long): RecordEntity?

    @Query("SELECT * FROM records")
    fun getAllRecords(): Flow<List<RecordEntity>>

    @Query("SELECT * FROM records WHERE individualId = :individualId")
    fun getRecordsByIndividualId(individualId: Long): Flow<List<RecordEntity>>

    @Query("SELECT * FROM observations WHERE recordId = :recordId")
    suspend fun getObservationByRecordId(recordId: Long): ObservationEntity?

    @Query("SELECT * FROM interventions WHERE recordId = :recordId")
    suspend fun getInterventionByRecordId(recordId: Long): InterventionEntity?

    @Query("SELECT * FROM future_events WHERE recordId = :recordId")
    suspend fun getFutureEventByRecordId(recordId: Long): FutureEventEntity?

    @Query("SELECT * FROM predicted_events WHERE futureEventId = :futureEventId")
    suspend fun getPredictedEventByFutureEventId(futureEventId: Long): PredictedEventEntity?

    @Query("SELECT * FROM planned_tasks WHERE futureEventId = :futureEventId")
    suspend fun getPlannedTaskByFutureEventId(futureEventId: Long): PlannedTaskEntity?

    @Query("SELECT * FROM waiting_delays WHERE futureEventId = :futureEventId")
    suspend fun getWaitingDelayByFutureEventId(futureEventId: Long): WaitingDelayEntity?
}
