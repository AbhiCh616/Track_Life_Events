package com.example.tracklifeevents.use_case

import com.example.tracklifeevents.repo_interface.EventRepo
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class ObserveAllEvents @Inject constructor(
    private val eventRepo: EventRepo
) {
    operator fun invoke() = eventRepo.observeAll().map {
        val today = LocalDate.now()
        it.filter { event ->
            !event.date.isBefore(today)
        }
    }
}