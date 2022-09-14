package com.example.tracklifeevents.local_source.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "event")
data class EventRow(

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "date")
    val date: LocalDate,

    @ColumnInfo(name = "image_uri")
    val imageUri: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
