package com.example.tfisland.model.dto

data class MissionResponse(
    val missionId: Long,
    val artistId: Long,
    val reward: Int,
    val title: String,
    val content: String
)
