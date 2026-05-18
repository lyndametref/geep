package net.madscientists.geep.core.database

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GeepMigrationTest {

    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        GeepDatabase::class.java,
        listOf(GeepDatabase.MIGRATION_2_3),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun create_version_1() {
        val db = helper.createDatabase(TEST_DB, 1)
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun migrate_1_to_2_autoMigration() {
        var db = helper.createDatabase(TEST_DB, 1)

        val values = ContentValues().apply {
            put("id", "sheep-v1")
            put("name", "Legacy Sheep")
            put("birthDate", "2023-01-01")
            put("sex", "FEMALE")
            put("living", 1)
            put("stillborn", 0)
        }
        db.insert("individuals", SQLiteDatabase.CONFLICT_REPLACE, values)
        db.close()

        db = helper.runMigrationsAndValidate(TEST_DB, 2, true)

        val cursor = db.query("SELECT * FROM individuals WHERE id = 'sheep-v1'")
        assertEquals(true, cursor.moveToFirst())

        val nameIndex = cursor.getColumnIndex("name")
        assertEquals("Legacy Sheep", cursor.getString(nameIndex))

        assertEquals(true, cursor.getColumnIndex("birthDate") != -1)
        assertEquals(true, cursor.getColumnIndex("sex") != -1)
        assertEquals(true, cursor.getColumnIndex("living") != -1)

        cursor.close()
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun migrate_2_to_3_dropsPortraitReference() {
        var db = helper.createDatabase(TEST_DB, 2)

        val values = ContentValues().apply {
            put("id", "sheep-v2")
            put("name", "Portrait Sheep")
            put("birthDate", "2024-06-01")
            put("sex", "MALE")
            put("living", 1)
            put("stillborn", 0)
            put("portraitReference", "ref/portrait-123")
            put("notes", "Has portrait")
        }
        db.insert("individuals", SQLiteDatabase.CONFLICT_REPLACE, values)
        db.close()

        db = helper.runMigrationsAndValidate(TEST_DB, 3, true, GeepDatabase.MIGRATION_2_3)

        // Verify data survived
        val cursor = db.query("SELECT * FROM individuals WHERE id = 'sheep-v2'")
        assertEquals(true, cursor.moveToFirst())

        val nameIndex = cursor.getColumnIndex("name")
        assertEquals("Portrait Sheep", cursor.getString(nameIndex))

        // Verify portraitReference column was dropped
        assertEquals(-1, cursor.getColumnIndex("portraitReference"))

        // Verify other columns still exist
        assertEquals(true, cursor.getColumnIndex("notes") != -1)
        assertEquals(true, cursor.getColumnIndex("birthDate") != -1)

        cursor.close()
        db.close()
    }
}
