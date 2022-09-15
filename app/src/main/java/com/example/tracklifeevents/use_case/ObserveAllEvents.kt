package com.example.tracklifeevents.use_case

import com.example.tracklifeevents.repo_interface.EventRepo
import javax.inject.Inject

class ObserveAllEvents @Inject constructor(
    private val eventRepo: EventRepo
) {
    operator fun invoke() = eventRepo.observeAll()
}