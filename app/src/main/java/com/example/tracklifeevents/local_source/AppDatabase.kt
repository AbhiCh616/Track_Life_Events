package com.example.tracklifeevents.local_source

import androidx.room.Database
import androidx.room.TypeConverters
import com.example.tracklifeevents.local_source.converter.LocalDateConverter
import com.example.tracklifeevents.local_source.dao.EventDao
import com.example.tracklifeevents.local_source.table.EventRow

@Database(
    version = 1,
    entities = [EventRow::class],
    exportSchema = true
)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase {
    abstract fun eventDao(): EventDao
}