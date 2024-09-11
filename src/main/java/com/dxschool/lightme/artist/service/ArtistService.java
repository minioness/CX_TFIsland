package com.dxschool.lightme.artist.service;

import com.dxschool.lightme.artist.controller.dto.ArtistResponse;
import com.dxschool.lightme.schedule.domain.ArtistSchedule;
import com.dxschool.lightme.artist.domain.repository.ArtistRepository;
import com.dxschool.lightme.schedule.domain.repository.ArtistScheduleRepository;
import com.dxschool.lightme.caseuser.domain.CaseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistScheduleRepository artistScheduleRepository;

    public List<ArtistResponse> findAll() {
        return artistRepository.findAll().stream()
                .map(ArtistResponse::from)
                .toList();
    }

    public ArtistSchedule getClosestSchedule(CaseUser caseUser) {
        return artistScheduleRepository
                .findAllByArtist_ArtistId(caseUser.getThemeArtist().getArtistId()).stream()
                .filter(schedule -> schedule.getScheduledAt().isAfter(LocalDateTime.now())) // 현재 시간 이후의 일정만 필터링
                .min(Comparator.comparing(schedule ->
                        Math.abs(Duration.between(LocalDateTime.now(), schedule.getScheduledAt()).toMillis())
                ))
                .orElseThrow(NoSuchElementException::new);
    }
}
