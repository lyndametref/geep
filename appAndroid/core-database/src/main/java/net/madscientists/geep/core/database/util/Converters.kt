package net.madscientists.geep.core.database.util

import androidx.room.TypeConverter
import net.madscientists.geep.core.model.DelayStatus
import net.madscientists.geep.core.model.PredictionStatus
import net.madscientists.geep.core.model.Sex
import net.madscientists.geep.core.model.TaskStatus
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromInstant(value: String?): Instant? {
        return value?.let { Instant.parse(it) }
    }

    @TypeConverter
    fun instantToString(instant: Instant?): String? {
        return instant?.toString()
    }

    @TypeConverter
    fun fromLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, dateFormatter) }
    }

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? {
        return date?.format(dateFormatter)
    }

    @TypeConverter
    fun fromSex(value: String?): Sex? {
        return value?.let { Sex.valueOf(it) }
    }

    @TypeConverter
    fun sexToString(sex: Sex?): String? {
        return sex?.name
    }

    @TypeConverter
    fun fromPredictionStatus(value: String?): PredictionStatus? {
        return value?.let { PredictionStatus.valueOf(it) }
    }

    @TypeConverter
    fun predictionStatusToString(status: PredictionStatus?): String? {
        return status?.name
    }

    @TypeConverter
    fun fromTaskStatus(value: String?): TaskStatus? {
        return value?.let { TaskStatus.valueOf(it) }
    }

    @TypeConverter
    fun taskStatusToString(status: TaskStatus?): String? {
        return status?.name
    }

    @TypeConverter
    fun fromDelayStatus(value: String?): DelayStatus? {
        return value?.let { DelayStatus.valueOf(it) }
    }

    @TypeConverter
    fun delayStatusToString(status: DelayStatus?): String? {
        return status?.name
    }
}
