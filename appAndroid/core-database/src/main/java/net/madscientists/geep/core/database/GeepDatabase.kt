package net.madscientists.geep.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Migration
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.madscientists.geep.core.database.dao.AttachmentDao
import net.madscientists.geep.core.database.dao.IndividualDao
import net.madscientists.geep.core.database.dao.RecordDao
import net.madscientists.geep.core.database.entity.AttachmentEntity
import net.madscientists.geep.core.database.entity.IndividualEntity
import net.madscientists.geep.core.database.entity.IndividualRecordCrossRef
import net.madscientists.geep.core.database.entity.RecordEntity
import net.madscientists.geep.core.database.util.Converters

@Database(
    entities = [
        IndividualEntity::class,
        RecordEntity::class,
        AttachmentEntity::class,
        IndividualRecordCrossRef::class
    ],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class GeepDatabase : RoomDatabase() {
    abstract fun individualDao(): IndividualDao
    abstract fun recordDao(): RecordDao
    abstract fun attachmentDao(): AttachmentDao

    companion object {
        val MIGRATION_2_3 = Migration(2, 3) { db ->
            db.execSQL("ALTER TABLE individuals DROP COLUMN portraitReference")
        }
    }
}
