package com.example.tracklifeevents.local_source.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromDate(date: LocalDate): String {
        return date.toString()
    }

    @TypeConverter
    fun toDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString)
    }
}