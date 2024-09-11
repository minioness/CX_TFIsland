package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.schedule.controller.dto.ArtistScheduleResponse;

import java.util.List;

public record CaseUserDetailResponse(
        CaseUserResponse caseUser,
        List<ImageResponse> images,
        List<VideoResponse> videos,
        List<MusicResponse> playlist,
        ArtistScheduleResponse upcomingSchedule,
        int dDay
) {
    public static CaseUserDetailResponse of(CaseUserResponse caseUser,
                                            List<ImageResponse> images,
                                            List<VideoResponse> videos,
                                            List<MusicResponse> playlist,
                                            ArtistScheduleResponse upcomingSchedule,
                                            int dDay
    ) {
        return new CaseUserDetailResponse(caseUser, images, videos, playlist, upcomingSchedule, dDay);
    }
}
