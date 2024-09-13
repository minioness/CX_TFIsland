package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.caseuser.domain.Video;

public record VideoResponse(
        Long videoId,
        String link,
        String title
) {
    public static VideoResponse from(Video video) {
        return new VideoResponse(
                video.getVideoId(),
                video.getVideoUrl(),
                video.getTitle()
        );
    }
}
