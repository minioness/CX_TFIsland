package com.dxschool.lightme.schedule.controller.dto;

import java.util.List;

public record ScheduleResponse(
        List<ArtistScheduleResponse> artistSchedules,
        List<UserScheduleResponse> userSchedules
) {
    public static ScheduleResponse of(List<ArtistScheduleResponse> artistSchedules,
                                      List<UserScheduleResponse> userSchedules) {
        return new ScheduleResponse(artistSchedules, userSchedules);
    }
}
