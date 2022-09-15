package com.example.tracklifeevents.local_source.dao

import androidx.room.*
import com.example.tracklifeevents.local_source.table.EventRow
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun observeAll(): Flow<List<EventRow>>

    @Delete
    suspend fun delete(eventRow: EventRow)

    @Insert
    suspend fun insert(eventRow: EventRow)

    @Update
    suspend fun update(eventRow: EventRow)

}