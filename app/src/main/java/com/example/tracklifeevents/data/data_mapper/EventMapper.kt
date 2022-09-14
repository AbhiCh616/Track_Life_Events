package com.example.tracklifeevents.data.data_mapper

import com.example.tracklifeevents.local_source.table.EventRow
import com.example.tracklifeevents.valid_model.ValidEvent

fun ValidEvent.toEventRow() = EventRow(
    name = this.name,
    date = this.date,
    imageUri = this.imageUri
)

fun EventRow.toValidEvent() = ValidEvent(
    name = this.name,
    date = this.date,
    imageUri = this.imageUri
)

fun List<EventRow>.toValidEventList() = this.map { eventRow ->
    eventRow.toValidEvent()
}