package com.dxschool.lightme.schedule.controller.dto;

import java.util.List;

public record ScheduleResponse(
        List<ArtistScheduleResponse> artistScheduleResponseList,
        List<CaseUserScheduleResponse> caseUserScheduleResponseList
) {
    public static ScheduleResponse of(List<ArtistScheduleResponse> artistSchedules,
                                      List<CaseUserScheduleResponse> userSchedules) {
        return new ScheduleResponse(artistSchedules, userSchedules);
    }
}
