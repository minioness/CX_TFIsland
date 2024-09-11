package com.dxschool.lightme.artist.controller.dto;

import com.dxschool.lightme.artist.domain.Artist;

public record ArtistResponse(
        Long artistId,
        String artistName,
        String color,
        String company,
        String logo
) {
    public static ArtistResponse from(Artist artist) {
        return new ArtistResponse(artist.getArtistId(), artist.getArtistName(), artist.getColor(), artist.getCompany(), artist.getLogo());
    }
}
