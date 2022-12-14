package com.example.tracklifeevents.use_case

import com.example.tracklifeevents.model.Event
import com.example.tracklifeevents.model_mapper.toValidEvent
import com.example.tracklifeevents.repo_interface.EventRepo
import javax.inject.Inject

class AddEvent @Inject constructor(
    private val eventRepo: EventRepo
) {
    suspend operator fun invoke(event: Event) {
        val validEvent = event.toValidEvent()
        eventRepo.insert(event = validEvent)
    }
}