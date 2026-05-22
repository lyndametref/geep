package net.madscientists.geep.core.model

import java.time.LocalDate

data class Individual(
    val id: String,
    val name: String? = null,
    val earTagId: String? = null,
    val birthDate: LocalDate,
    val deathDate: LocalDate? = null,
    val sex: Sex,
    val colorPattern: String? = null,
    val living: Boolean = true,
    val stillborn: Boolean = false,
    val belongsToFlock: Boolean = true,
    val sireId: String? = null,
    val damId: String? = null,
    val notes: String? = null
)
