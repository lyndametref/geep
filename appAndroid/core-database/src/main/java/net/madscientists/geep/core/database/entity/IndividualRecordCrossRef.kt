package net.madscientists.geep.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "individual_record_cross_ref",
    primaryKeys = ["individualId", "recordId"],
    foreignKeys = [
        ForeignKey(
            entity = IndividualEntity::class,
            parentColumns = ["id"],
            childColumns = ["individualId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RecordEntity::class,
            parentColumns = ["id"],
            childColumns = ["recordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("individualId"),
        Index("recordId")
    ]
)
data class IndividualRecordCrossRef(
    val individualId: String,
    val recordId: String
)
