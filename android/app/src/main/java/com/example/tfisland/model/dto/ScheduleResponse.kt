package com.example.tfisland.model.dto

data class ScheduleResponse(
    val artistScheduleResponseList: List<ArtistScheduleResponse>,
    val caseUserScheduleResponseList: List<CaseUserScheduleResponse>
)
