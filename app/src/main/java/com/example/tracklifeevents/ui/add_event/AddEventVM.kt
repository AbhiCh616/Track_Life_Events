package com.example.tracklifeevents.ui.add_event

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tracklifeevents.model.Event
import com.example.tracklifeevents.use_case.AddEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddEventVM @Inject constructor(
    private val addEvent: AddEvent
) : ViewModel() {
    var eventName = ""
    var eventDate = MutableStateFlow<LocalDate?>(null)
    var eventDateText = eventDate.map {
        it?.toString() ?: ""
    }
    private val _eventImageUri = MutableStateFlow<String?>(null)
    val eventImageUri = _eventImageUri.map { it }
    private val _snackbar = MutableStateFlow<AddEventSnackbars?>(null)
    val snackbar = _snackbar.map { it }
    private val _goBack = MutableStateFlow(false)
    val goBack = _goBack.map { it }

    fun onSaveClick() = viewModelScope.launch {
        if (eventName.isEmpty()) {
            _snackbar.update { AddEventSnackbars.EMPTY_NAME }
            return@launch
        }
        if (eventDate.value == null) {
            _snackbar.update { AddEventSnackbars.EMPTY_DATE }
            return@launch
        }
        if (_eventImageUri.value == null) {
            _snackbar.update { AddEventSnackbars.EMPTY_IMAGE }
            return@launch
        }
        val event = Event(
            name = eventName,
            date = eventDate.value,
            imageUri = _eventImageUri.value
        )
        addEvent(event = event)
        _goBack.update { true }
    }

    fun setDate(year: Int, month: Int, day: Int) {
        Log.d("DATE", month.toString())
        Log.d("DATE", LocalDate.of(year, month, day).toString())
        eventDate.update { LocalDate.of(year, month, day) }
    }

    fun setImage(uri: Uri?) {
        _eventImageUri.update { uri.toString() }
    }

    fun dialogShown() {
        _snackbar.update { null }
    }
}