package com.dxschool.lightme.schedule.controller.dto;

import com.dxschool.lightme.schedule.domain.ArtistSchedule;
import com.dxschool.lightme.common.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ArtistScheduleResponse(
    Long scheduleId,
    Category category,
    String title,
    LocalDateTime scheduledAt,
    BigDecimal latitude,
    BigDecimal longitude
) {
    public static ArtistScheduleResponse from(ArtistSchedule schedule) {
        return new ArtistScheduleResponse(
                schedule.getArtistScheduleId(),
                schedule.getCategory(),
                schedule.getTitle(),
                schedule.getScheduledAt(),
                schedule.getLatitude(),
                schedule.getLongitude()
        );
    }
}
