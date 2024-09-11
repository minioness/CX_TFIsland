package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.common.domain.Category;
import com.dxschool.lightme.caseuser.domain.UserSchedule;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserScheduleResponse(
        Long scheduleId,
        Category category,
        String title,
        LocalDateTime scheduledAt,
        BigDecimal latitude,
        BigDecimal longitude
) {
    public static UserScheduleResponse from(UserSchedule schedule) {
        return new UserScheduleResponse(
                schedule.getUserScheduleId(),
                schedule.getCategory(),
                schedule.getTitle(),
                schedule.getScheduledAt(),
                schedule.getLatitude(),
                schedule.getLongitude()
        );
    }
}
