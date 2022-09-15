package com.example.tracklifeevents.ui.events_list

import androidx.lifecycle.ViewModel
import com.example.tracklifeevents.use_case.ObserveAllEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class EventListVM @Inject constructor(
    private val observeAllEvents: ObserveAllEvents
) : ViewModel() {
    val events = observeAllEvents().map {
        it.toEventItemData()
    }
}