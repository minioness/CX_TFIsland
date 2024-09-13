package com.example.tfisland.model.dto

data class ArtistScheduleResponse(
    val scheduleId: Long,
    val artistId: Long,
    val category: String,
    val title: String,
    val scheduledAt: String,
    val latitude: Long?,
    val longitude: Long?
)
