package com.example.tfisland.model.data

import com.example.tfisland.model.dto.ArtistScheduleResponse
import com.example.tfisland.model.dto.CaseUserScheduleResponse

sealed class ScheduleItem {
    abstract val scheduledAt: String

    data class UserSchedule(val caseUserSchedule: CaseUserScheduleResponse) : ScheduleItem() {
        override val scheduledAt: String
            get() = caseUserSchedule.scheduledAt
    }

    data class ArtistSchedule(val artistSchedule: ArtistScheduleResponse) : ScheduleItem() {
        override val scheduledAt: String
            get() = artistSchedule.scheduledAt
    }
}
