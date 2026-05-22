package net.madscientists.geep.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.madscientists.geep.core.database.dao.AttachmentDao
import net.madscientists.geep.core.database.dao.IndividualDao
import net.madscientists.geep.core.database.dao.RecordDao
import net.madscientists.geep.core.database.entity.AttachmentEntity
import net.madscientists.geep.core.database.entity.FutureEventEntity
import net.madscientists.geep.core.database.entity.IndividualEntity
import net.madscientists.geep.core.database.entity.InterventionEntity
import net.madscientists.geep.core.database.entity.ObservationEntity
import net.madscientists.geep.core.database.entity.PlannedTaskEntity
import net.madscientists.geep.core.database.entity.PredictedEventEntity
import net.madscientists.geep.core.database.entity.RecordEntity
import net.madscientists.geep.core.database.entity.WaitingDelayEntity
import net.madscientists.geep.core.database.util.Converters

@Database(
    entities = [
        IndividualEntity::class,
        RecordEntity::class,
        ObservationEntity::class,
        InterventionEntity::class,
        FutureEventEntity::class,
        PredictedEventEntity::class,
        PlannedTaskEntity::class,
        WaitingDelayEntity::class,
        AttachmentEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class GeepDatabase : RoomDatabase() {
    abstract fun individualDao(): IndividualDao
    abstract fun recordDao(): RecordDao
    abstract fun attachmentDao(): AttachmentDao

    companion object {
    }
}
