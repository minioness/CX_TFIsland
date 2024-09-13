package com.example.tfisland.model.dto

data class MissionLogResponse(
    val caseUserId: Long,
    val missionId: Long,
    val status: String?
)
