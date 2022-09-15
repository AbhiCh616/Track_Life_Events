package com.example.tracklifeevents.ui.events_list

import android.net.Uri
import com.example.tracklifeevents.valid_model.ValidEvent
import java.time.LocalDate

data class EventItemData(
    val name: String,
    val date: LocalDate,
    val imageUri: Uri,
)

fun ValidEvent.toEventItemData() = EventItemData(
    name = this.name,
    date = this.date,
    imageUri = Uri.parse(imageUri)
)

fun List<ValidEvent>.toEventItemData() = this.map {
    it.toEventItemData()
}