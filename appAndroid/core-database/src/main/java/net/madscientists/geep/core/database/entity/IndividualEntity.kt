package net.madscientists.geep.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import net.madscientists.geep.core.model.Sex
import java.time.LocalDate

@Entity(tableName = "individuals")
data class IndividualEntity(
    @PrimaryKey
    val id: Long,
    val name: String?,
    @ColumnInfo(name = "earTagId")
    val earTagId: String?,
    val birthDate: LocalDate,
    val deathDate: LocalDate?,
    val sex: Sex,
    val colorPattern: String?,
    val living: Boolean,
    val stillborn: Boolean,
    val belongsToFlock: Boolean,
    val sireId: Long?,
    val damId: Long?,
    val notes: String? = null
)
