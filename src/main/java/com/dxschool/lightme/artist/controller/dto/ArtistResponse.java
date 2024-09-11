package com.dxschool.lightme.artist.controller.dto;

import com.dxschool.lightme.artist.domain.Artist;

public record ArtistResponse(
        Long artistId,
        String color,
        String company,
        String logo
) {
    public static ArtistResponse from(Artist artist) {
        return new ArtistResponse(artist.getArtistId(), artist.getColor(), artist.getCompany(), artist.getLogo());
    }
}
