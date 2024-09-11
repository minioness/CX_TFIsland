package com.dxschool.lightme.mission.controller.dto;

import com.dxschool.lightme.artist.controller.dto.ArtistResponse;
import com.dxschool.lightme.mission.domain.Mission;
import com.dxschool.lightme.mission.domain.MissionStatus;

public record MissionResponse(
        Long missionId,
        ArtistResponse artist,
        Long reward,
        String title,
        String content,
        MissionStatus status
) {
    public static MissionResponse from(Mission mission, MissionStatus status) {
        return new MissionResponse(
                mission.getMissionId(),
                ArtistResponse.from(mission.getArtist()),
                mission.getReward(),
                mission.getTitle(),
                mission.getDescription(),
                status
        );
    }
}
