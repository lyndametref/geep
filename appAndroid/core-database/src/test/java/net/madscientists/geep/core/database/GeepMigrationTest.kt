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
        listOf(), // No manual migrations needed as we use AutoMigration
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
        // 1. Create DB at version 1
        var db = helper.createDatabase(TEST_DB, 1)

        // 2. Insert data using raw SQLite
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

        // 3. Migrate to version 2
        // runMigrationsAndValidate will automatically detect AutoMigrations if defined in GeepDatabase
        db = helper.runMigrationsAndValidate(TEST_DB, 2, true)

        // 4. Verify data integrity and schema consistency
        val cursor = db.query("SELECT * FROM individuals WHERE id = 'sheep-v1'")
        assertEquals(true, cursor.moveToFirst())
        
        // Verify old data survived
        val nameIndex = cursor.getColumnIndex("name")
        assertEquals("Legacy Sheep", cursor.getString(nameIndex))

        // Verify baseline columns remain available after migration
        assertEquals(true, cursor.getColumnIndex("birthDate") != -1)
        assertEquals(true, cursor.getColumnIndex("sex") != -1)
        assertEquals(true, cursor.getColumnIndex("living") != -1)
        
        cursor.close()
        db.close()
    }
}
