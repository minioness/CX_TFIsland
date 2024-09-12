package com.dxschool.lightme.schedule.controller.dto;

import com.dxschool.lightme.schedule.domain.UserSchedule;

import java.time.LocalDateTime;

public record CaseUserScheduleResponse(
        Long scheduleId,
        String category,
        String title,
        LocalDateTime scheduledAt,
        Long latitude,
        Long longitude
) {
    public static CaseUserScheduleResponse from(UserSchedule schedule) {
        return new CaseUserScheduleResponse(
                schedule.getUserScheduleId(),
                schedule.getCategory().name(),
                schedule.getTitle(),
                schedule.getScheduledAt(),
                schedule.getLatitude().longValue(),
                schedule.getLongitude().longValue()
        );
    }
}
