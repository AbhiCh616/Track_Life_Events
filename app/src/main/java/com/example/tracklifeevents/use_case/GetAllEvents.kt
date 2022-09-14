package com.example.tracklifeevents.use_case

import com.example.tracklifeevents.repo_interface.EventRepo

class GetAllEvents(
    private val eventRepo: EventRepo
) {
    operator fun invoke() = eventRepo.getAll()
}