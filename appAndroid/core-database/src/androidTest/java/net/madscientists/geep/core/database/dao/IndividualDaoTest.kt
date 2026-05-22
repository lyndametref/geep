package net.madscientists.geep.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import net.madscientists.geep.core.database.GeepDatabase
import net.madscientists.geep.core.database.entity.IndividualEntity
import net.madscientists.geep.core.model.Sex
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class IndividualDaoTest {

    private lateinit var db: GeepDatabase
    private lateinit var individualDao: IndividualDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GeepDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        individualDao = db.individualDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertIndividual_WithValidData_InsertsIndividual() = runBlocking {
        val individual = IndividualEntity(
            id = 1L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        individualDao.insertIndividual(individual)
        
        val readIndividual = individualDao.getIndividualById(1L)
        assertNotNull(readIndividual)
        assertEquals(individual, readIndividual)
    }

    @Test
    fun insertIndividual_WithDuplicateId_ReplacesExistingIndividual() = runBlocking {
        val individual1 = IndividualEntity(
            id = 1L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        val individual2 = IndividualEntity(
            id = 1L,
            name = "Bessie Updated",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = false,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        
        individualDao.insertIndividual(individual1)
        individualDao.insertIndividual(individual2)
        
        val readIndividual = individualDao.getIndividualById(1L)
        assertNotNull(readIndividual)
        assertEquals("Bessie Updated", readIndividual?.name)
        assertEquals(false, readIndividual?.living)
    }

    @Test
    fun updateIndividual_WithValidData_UpdatesIndividual() = runBlocking {
        val individual = IndividualEntity(
            id = 1L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        individualDao.insertIndividual(individual)
        
        val updatedIndividual = individual.copy(name = "Bessie Updated", living = false)
        individualDao.updateIndividual(updatedIndividual)
        
        val readIndividual = individualDao.getIndividualById(1L)
        assertNotNull(readIndividual)
        assertEquals("Bessie Updated", readIndividual?.name)
        assertEquals(false, readIndividual?.living)
    }

    @Test
    fun deleteIndividual_WithExistingIndividual_DeletesIndividual() = runBlocking {
        val individual = IndividualEntity(
            id = 1L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        individualDao.insertIndividual(individual)
        
        individualDao.deleteIndividual(individual)
        val readIndividual = individualDao.getIndividualById(1L)
        assertNull(readIndividual)
    }

    @Test
    fun getAllIndividuals_WithMultipleIndividuals_ReturnsAllIndividuals() = runBlocking {
        val individual1 = IndividualEntity(
            id = 1L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        val individual2 = IndividualEntity(
            id = 2L,
            name = "Dolly",
            earTagId = "CH 120.1234.5678.8",
            birthDate = LocalDate.of(2023, 3, 16),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "Black",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        
        individualDao.insertIndividual(individual1)
        individualDao.insertIndividual(individual2)
        
        val individuals = individualDao.getAllIndividuals().first()
        assertEquals(2, individuals.size)
    }

    @Test
    fun getIndividualById_WithNonExistentId_ReturnsNull() = runBlocking {
        val individual = individualDao.getIndividualById(999L)
        assertNull(individual)
    }

    @Test
    fun updateIndividual_WithNonExistentIndividual_DoesNotThrow() = runBlocking {
        val individual = IndividualEntity(
            id = 999L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        // Should not throw an exception
        individualDao.updateIndividual(individual)
    }

    @Test
    fun deleteIndividual_WithNonExistentIndividual_DoesNotThrow() = runBlocking {
        val individual = IndividualEntity(
            id = 999L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        // Should not throw an exception
        individualDao.deleteIndividual(individual)
    }

    @Test
    fun insertIndividual_WithNullEarTagId_InsertsIndividual() = runBlocking {
        val individual = IndividualEntity(
            id = 1L,
            name = "Bessie",
            earTagId = null,
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = "White",
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        individualDao.insertIndividual(individual)
        
        val readIndividual = individualDao.getIndividualById(1L)
        assertNotNull(readIndividual)
        assertEquals(individual, readIndividual)
    }

    @Test
    fun insertIndividual_WithNullColorPattern_InsertsIndividual() = runBlocking {
        val individual = IndividualEntity(
            id = 1L,
            name = "Bessie",
            earTagId = "CH 120.1234.5678.9",
            birthDate = LocalDate.of(2023, 3, 15),
            deathDate = null,
            sex = Sex.FEMALE,
            colorPattern = null,
            living = true,
            stillborn = false,
            belongsToFlock = true,
            sireId = null,
            damId = null
        )
        individualDao.insertIndividual(individual)
        
        val readIndividual = individualDao.getIndividualById(1L)
        assertNotNull(readIndividual)
        assertEquals(individual, readIndividual)
    }
}