package com.example.tfisland.model.dto

import java.time.LocalDate

data class MemoryRequest(
    val content: String,
    val title: String,
    val memoryDate: LocalDate
)
