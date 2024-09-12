package com.dxschool.lightme.schedule.controller.dto;

import com.dxschool.lightme.schedule.domain.ArtistSchedule;
import com.dxschool.lightme.common.domain.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ArtistScheduleResponse(
    Long scheduleId,
    Long artistId,
    String category,
    String title,
    LocalDateTime scheduledAt,
    Long latitude,
    Long longitude
) {
    public static ArtistScheduleResponse from(ArtistSchedule schedule) {
        return new ArtistScheduleResponse(
                schedule.getArtistScheduleId(),
                schedule.getArtist().getArtistId(),
                schedule.getCategory().name(),
                schedule.getTitle(),
                schedule.getScheduledAt(),
                schedule.getLatitude().longValue(),
                schedule.getLongitude().longValue()
        );
    }
}
