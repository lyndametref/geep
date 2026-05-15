package net.madscientists.geep.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import net.madscientists.geep.core.database.entity.IndividualEntity

/**
 * Core CRUD operations for Individuals.
 * Feature-specific queries (genealogy traversal, search filters) are out of scope
 * for this initial baseline and will be added in future feature-specific tasks.
 */
@Dao
interface IndividualDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndividual(individual: IndividualEntity)

    @Update
    suspend fun updateIndividual(individual: IndividualEntity)

    @Delete
    suspend fun deleteIndividual(individual: IndividualEntity)

    @Query("SELECT * FROM individuals WHERE id = :id")
    suspend fun getIndividualById(id: String): IndividualEntity?

    @Query("SELECT * FROM individuals")
    fun getAllIndividuals(): Flow<List<IndividualEntity>>
}
