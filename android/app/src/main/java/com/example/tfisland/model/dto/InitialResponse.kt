package com.example.tfisland.model.dto

data class InitialResponse(
    val caseUserDTO: CaseUserResponse,
    val imageDTO: ImageResponse,
    val videoDTO: VideoResponse,
    val day: Int,
    val scheduleTitle: String
)
