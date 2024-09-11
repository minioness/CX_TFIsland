package com.dxschool.lightme.artist.controller;

import com.dxschool.lightme.artist.controller.dto.ArtistResponse;
import com.dxschool.lightme.artist.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/artists")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistResponse>> findArtists() {
        return ResponseEntity.ok(artistService.findAll());
    }
}
