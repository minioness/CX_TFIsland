package com.example.tfisland.model.dto

data class CaseUserScheduleResponse(
    val scheduleId: Long,
    val category: String,
    val title: String,
    val scheduledAt: String,
    val latitude: Long?,
    val longitude: Long?
)
