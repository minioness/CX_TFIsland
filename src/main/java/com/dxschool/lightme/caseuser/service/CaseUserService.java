package com.dxschool.lightme.caseuser.service;

import com.dxschool.lightme.schedule.controller.dto.ArtistScheduleResponse;
import com.dxschool.lightme.artist.service.ArtistService;
import com.dxschool.lightme.caseuser.controller.dto.*;
import com.dxschool.lightme.artist.domain.Artist;
import com.dxschool.lightme.schedule.domain.ArtistSchedule;
import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.artist.domain.repository.ArtistRepository;
import com.dxschool.lightme.caseuser.domain.repository.CaseUserRepository;
import com.dxschool.lightme.common.util.AddressUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CaseUserService {

    private final CaseUserRepository caseUserRepository;
    private final ArtistRepository artistRepository;
    private final ArtistService artistService;
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

    public CaseUserDetailResponse getCaseUserDetail(Long userId) {
        CaseUser caseUser = caseUserRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new);

        ArtistSchedule closestSchedule = artistService.getClosestSchedule(caseUser);
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

    public List<CaseUserResponse> findNearby(Long userId) {
        CaseUser caseUser = caseUserRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        Long addressId = caseUser.getAddress().getAddressId();
        return caseUserRepository.findTop20ByAddress_AddressId(addressId).stream()
                .map(CaseUserResponse::from)
                .toList();

    }
}
