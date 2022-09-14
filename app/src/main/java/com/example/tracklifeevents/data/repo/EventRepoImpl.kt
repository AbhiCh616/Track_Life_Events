package com.example.tracklifeevents.data.repo

import com.example.tracklifeevents.data.data_mapper.toEventRow
import com.example.tracklifeevents.data.data_mapper.toValidEventList
import com.example.tracklifeevents.local_source.dao.EventDao
import com.example.tracklifeevents.repo_interface.EventRepo
import com.example.tracklifeevents.valid_model.ValidEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventRepoImpl(
    private val eventDao: EventDao
) : EventRepo {

    override fun getAll(): Flow<List<ValidEvent>> = eventDao.getAll().map { list ->
        list.toValidEventList()
    }

    override suspend fun delete(event: ValidEvent) {
        val eventRow = event.toEventRow()
        eventDao.delete(eventRow)
    }

    override suspend fun insert(event: ValidEvent) {
        val eventRow = event.toEventRow()
        eventDao.insert(eventRow)
    }

    override suspend fun update(event: ValidEvent) {
        val eventRow = event.toEventRow()
        eventDao.update(eventRow)
    }

}