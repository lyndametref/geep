package net.madscientists.geep.core.database.util

import androidx.room.TypeConverter
import net.madscientists.geep.core.model.FutureEventStatus
import net.madscientists.geep.core.model.Sex
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it, dateTimeFormatter) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.format(dateTimeFormatter)
    }

    @TypeConverter
    fun fromLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, dateFormatter) }
    }

    @TypeConverter
    fun localDateToTimestamp(date: LocalDate?): String? {
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
    fun fromFutureEventStatus(value: String?): FutureEventStatus? {
        return value?.let { FutureEventStatus.valueOf(it) }
    }

    @TypeConverter
    fun futureEventStatusToString(status: FutureEventStatus?): String? {
        return status?.name
    }
}
