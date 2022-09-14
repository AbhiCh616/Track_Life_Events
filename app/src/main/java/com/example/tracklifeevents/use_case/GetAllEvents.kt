package com.example.tracklifeevents.use_case

import com.example.tracklifeevents.repo_interface.EventRepo
import javax.inject.Inject

class GetAllEvents @Inject constructor(
    private val eventRepo: EventRepo
) {
    operator fun invoke() = eventRepo.getAll()
}