package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.caseuser.domain.Music;

public record MusicResponse(
        Long musicId,
        String artist,
        String title
) {
    public static MusicResponse from(Music music) {
        return new MusicResponse(
                music.getMusicId(),
                music.getArtist().getArtistName(),
                music.getTitle()
        );
    }
}
