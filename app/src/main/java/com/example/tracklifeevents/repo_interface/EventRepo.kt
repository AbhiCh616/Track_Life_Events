package com.example.tracklifeevents.repo_interface

import com.example.tracklifeevents.valid_model.ValidEvent
import kotlinx.coroutines.flow.Flow

interface EventRepo {
    fun getAll(): Flow<List<ValidEvent>>
    suspend fun delete(event: ValidEvent)
    suspend fun insert(event: ValidEvent)
    suspend fun update(event: ValidEvent)
}