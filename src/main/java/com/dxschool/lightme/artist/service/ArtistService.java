package com.dxschool.lightme.artist.service;

import com.dxschool.lightme.artist.controller.dto.ArtistResponse;
import com.dxschool.lightme.artist.domain.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public List<ArtistResponse> findAll() {
        return artistRepository.findAll().stream()
                .map(ArtistResponse::from)
                .toList();
    }
}
