package com.example.tracklifeevents.ui.events_list

import android.net.Uri
import com.example.tracklifeevents.valid_model.ValidEvent
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class EventItemData(
    val name: String,
    val daysLeft: String,
    val imageUri: Uri,
)

fun ValidEvent.toEventItemData() = EventItemData(
    name = this.name,
    daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), this.date).toInt().toString(),
    imageUri = Uri.parse(imageUri)
)

fun List<ValidEvent>.toEventItemData() = this.map {
    it.toEventItemData()
}