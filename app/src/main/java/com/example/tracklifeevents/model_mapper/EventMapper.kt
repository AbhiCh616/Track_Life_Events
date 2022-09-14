package com.example.tracklifeevents.model_mapper

import com.example.tracklifeevents.exception.EventDateEmptyException
import com.example.tracklifeevents.exception.EventImageEmptyException
import com.example.tracklifeevents.exception.EventNameEmptyException
import com.example.tracklifeevents.model.Event
import com.example.tracklifeevents.valid_model.ValidEvent

fun Event.toValidEvent(): ValidEvent {
    if (this.name == null) {
        throw EventNameEmptyException()
    }
    if (this.date == null) {
        throw EventDateEmptyException()
    }
    if (this.imageUri == null) {
        throw EventImageEmptyException()
    }
    return ValidEvent(
        name = this.name,
        date = this.date,
        imageUri = this.imageUri
    )
}