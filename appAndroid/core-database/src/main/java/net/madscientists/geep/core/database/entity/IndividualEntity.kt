package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.madscientists.geep.core.model.Sex
import java.time.LocalDate

@Entity(tableName = "individuals")
data class IndividualEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val bdtaNumber: String?,
    val birthDate: LocalDate,
    val deathDate: LocalDate?,
    val sex: Sex,
    val colorPattern: String?,
    val living: Boolean,
    val stillborn: Boolean,
    val portraitReference: String?,
    val sireId: String?,
    val damId: String?,
    val notes: String? = null
)
