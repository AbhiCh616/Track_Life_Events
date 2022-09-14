package com.example.tracklifeevents.valid_model

import java.time.LocalDate

data class ValidEvent(
    val name: String,
    val date: LocalDate,
    val imageUri: String
)
