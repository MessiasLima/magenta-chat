package io.github.messiaslima.core.database.common

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    @TypeConverter
    fun toDate(value: Long) = Date(value)

    @TypeConverter
    fun fromDate(date: Date) = date.time
}