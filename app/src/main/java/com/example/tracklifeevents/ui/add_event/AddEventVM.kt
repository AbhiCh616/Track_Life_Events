package com.example.tracklifeevents.ui.add_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracklifeevents.model.Event
import com.example.tracklifeevents.use_case.AddEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEventVM @Inject constructor(
    private val addEvent: AddEvent
) : ViewModel() {
    var eventName = ""
    var eventDate = ""
    var eventImageUri = ""

    fun onSaveClick() = viewModelScope.launch {
        val event = Event(
            name = eventName,
            date = null,
            imageUri = eventImageUri
        )
        addEvent(event = event)
    }
}