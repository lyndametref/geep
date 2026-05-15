package net.madscientists.geep.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import net.madscientists.geep.core.database.entity.IndividualRecordCrossRef
import net.madscientists.geep.core.database.entity.RecordEntity

/**
 * Core CRUD operations for Records and their associations.
 * Feature-specific filtering (journaling, calendar projections) is out of scope 
 * and will be implemented in future feature-specific tasks.
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
    suspend fun insertIndividualRecordCrossRef(crossRef: IndividualRecordCrossRef)

    /**
     * Core transactional logic for batch-applying a record to multiple individuals.
     */
    @Transaction
    suspend fun insertBatchRecord(record: RecordEntity, individualIds: List<String>) {
        insertRecord(record)
        individualIds.forEach { id ->
            insertIndividualRecordCrossRef(IndividualRecordCrossRef(id, record.id))
        }
    }

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getRecordById(id: String): RecordEntity?

    @Query("SELECT * FROM records")
    fun getAllRecords(): Flow<List<RecordEntity>>
    
    @Query("SELECT * FROM individual_record_cross_ref")
    fun getAllRecordAssociations(): Flow<List<IndividualRecordCrossRef>>
}
