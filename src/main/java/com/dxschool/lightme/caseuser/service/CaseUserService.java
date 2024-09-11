package com.dxschool.lightme.caseuser.service;

import com.dxschool.lightme.artist.controller.dto.ArtistScheduleResponse;
import com.dxschool.lightme.caseuser.controller.dto.*;
import com.dxschool.lightme.artist.domain.Artist;
import com.dxschool.lightme.artist.domain.ArtistSchedule;
import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.artist.domain.repository.ArtistRepository;
import com.dxschool.lightme.artist.domain.repository.ArtistScheduleRepository;
import com.dxschool.lightme.caseuser.domain.repository.CaseUserRepository;
import com.dxschool.lightme.common.util.AddressUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CaseUserService {

    private final CaseUserRepository caseUserRepository;
    private final ArtistRepository artistRepository;
    private final ArtistScheduleRepository artistScheduleRepository;
    private final HttpSession httpSession;

    public CaseUserResponse find(Long userId) {
        CaseUser caseUser = caseUserRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new);

        return CaseUserResponse.from(caseUser);
    }

    @Transactional
    public void registerCaseUser(CaseUserRequest request) {
        Artist themeArtist = artistRepository.findById(request.artistId())
                .orElseThrow(NoSuchElementException::new);

        CaseUser caseUser = CaseUser.builder()
                .address(AddressUtil.parseAddress(request.address()))
                .nickname(request.nickname())
                .profileLink(request.profileLink())
                .themeArtist(themeArtist)
                .build();

        httpSession.setAttribute("userId",caseUser.getCaseUserId());
        caseUserRepository.save(caseUser);
    }

    private ArtistSchedule getClosestSchedule(CaseUser caseUser) {
        return artistScheduleRepository
                .findAllByArtistId(caseUser.getThemeArtist().getArtistId()).stream()
                .filter(schedule -> schedule.getScheduledAt().isAfter(LocalDateTime.now())) // 현재 시간 이후의 일정만 필터링
                .min(Comparator.comparing(schedule ->
                        Math.abs(Duration.between(LocalDateTime.now(), schedule.getScheduledAt()).toMillis())
                ))
                .orElseThrow(NoSuchElementException::new);
    }

    public CaseUserDetailResponse getCaseUserDetail(Long userId) {
        CaseUser caseUser = caseUserRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new);

        ArtistSchedule closestSchedule = getClosestSchedule(caseUser);
        int dDay = (int)Duration.between(LocalDateTime.now(), closestSchedule.getScheduledAt()).toDays();

        return CaseUserDetailResponse.of(
                CaseUserResponse.from(caseUser),
                caseUser.getImages().stream()
                        .map(ImageResponse::from)
                        .toList(),
                caseUser.getVideos().stream()
                        .map(VideoResponse::from)
                        .toList(),
                caseUser.getPlaylist().stream()
                        .map(MusicResponse::from)
                        .toList(),
                ArtistScheduleResponse.from(closestSchedule),
                dDay
        );
    }

    @Transactional
    public void updateCaseUser(Long artistId, Long userId) {
        CaseUser caseUser = caseUserRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(NoSuchElementException::new);

        caseUser.updateThemeArtist(artist);
    }
}
