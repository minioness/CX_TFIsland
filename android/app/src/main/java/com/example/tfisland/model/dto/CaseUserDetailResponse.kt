package com.example.tfisland.model.dto

data class CaseUserDetailResponse(
    val caseUserResponse: CaseUserResponse,
    val imageResponseList: List<ImageResponse>,
    val videoResponseList: List<VideoResponse>,
    val musicResponseList: List<MusicResponse>,
    val artistScheduleReponse: ArtistScheduleResponse,
    val dDay: Int
)
