package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "records",
    foreignKeys = [
        ForeignKey(
            entity = IndividualEntity::class,
            parentColumns = ["id"],
            childColumns = ["individualId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("individualId")]
)
data class RecordEntity(
    @PrimaryKey
    val id: Long,
    val timestamp: Instant,
    val recordType: String,
    val individualId: Long?,
    val sourceRecordId: Long?
)
