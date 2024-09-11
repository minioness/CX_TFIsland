package com.dxschool.lightme.schedule.service;

import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.caseuser.domain.repository.CaseUserRepository;
import com.dxschool.lightme.schedule.controller.dto.ArtistScheduleResponse;
import com.dxschool.lightme.schedule.controller.dto.ScheduleResponse;
import com.dxschool.lightme.schedule.controller.dto.UserScheduleResponse;
import com.dxschool.lightme.schedule.domain.repository.ArtistScheduleRepository;
import com.dxschool.lightme.schedule.domain.repository.UserScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ArtistScheduleRepository artistScheduleRepository;
    private final UserScheduleRepository userScheduleRepository;
    private final CaseUserRepository caseUserRepository;

    public ScheduleResponse findAll(Long userId) {
        CaseUser caseUser = caseUserRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
        Long themeArtistId = caseUser.getThemeArtist().getArtistId();

        return ScheduleResponse.of(
                artistScheduleRepository.findAllByArtist_ArtistId(themeArtistId).stream()
                        .map(ArtistScheduleResponse::from)
                        .toList(),
                userScheduleRepository.findByUser_CaseUserId(userId).stream()
                        .map(UserScheduleResponse::from)
                        .toList()
        );
    }
}
