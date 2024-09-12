package com.dxschool.lightme.schedule.service;

import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.caseuser.domain.repository.CaseUserRepository;
import com.dxschool.lightme.schedule.controller.dto.ArtistScheduleResponse;
import com.dxschool.lightme.schedule.controller.dto.RecentScheduleResponse;
import com.dxschool.lightme.schedule.controller.dto.ScheduleResponse;
import com.dxschool.lightme.schedule.controller.dto.UserScheduleResponse;
import com.dxschool.lightme.schedule.domain.ArtistSchedule;
import com.dxschool.lightme.schedule.domain.Schedule;
import com.dxschool.lightme.schedule.domain.UserSchedule;
import com.dxschool.lightme.schedule.domain.repository.ArtistScheduleRepository;
import com.dxschool.lightme.schedule.domain.repository.UserScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

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

    public RecentScheduleResponse findRecent(Long userId) {
        CaseUser caseUser = caseUserRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new);

        LocalDateTime now = LocalDateTime.now();
        ArtistSchedule closesArtistSchedule = getclosestArtistSchedule(caseUser.getThemeArtist().getArtistId());
        UserSchedule closestUserSchedule = getClosestUserSchedule(caseUser.getCaseUserId());

        Schedule closestSchedule = Stream.of(
                        closestUserSchedule, closesArtistSchedule
                ).min(Comparator.comparing(Schedule::scheduledAt)) // 가장 가까운 scheduledAt 찾기
                .orElseThrow(NoSuchElementException::new);

        int dDay = (int) Duration.between(now, closestSchedule.scheduledAt()).toDays();

        return RecentScheduleResponse.of(
                dDay,
                closestSchedule.title()
        );
    }

    private ArtistSchedule getclosestArtistSchedule(Long artistId) {
        return artistScheduleRepository.findAllByArtist_ArtistId(artistId).stream()
                .filter(schedule -> schedule.getScheduledAt().isAfter(LocalDateTime.now())) // 현재 시간 이후의 일정만 필터링
                .min(Comparator.comparing(schedule ->
                        Math.abs(Duration.between(LocalDateTime.now(), schedule.getScheduledAt()).toMillis())
                ))
                .orElseThrow(NoSuchElementException::new);
    }

    private UserSchedule getClosestUserSchedule(Long userId) {
        return userScheduleRepository.findByUser_CaseUserId(userId).stream()
                .filter(schedule -> schedule.getScheduledAt().isAfter(LocalDateTime.now())) // 현재 시간 이후의 일정만 필터링
                .min(Comparator.comparing(schedule ->
                        Math.abs(Duration.between(LocalDateTime.now(), schedule.getScheduledAt()).toMillis())
                ))
                .orElseThrow(NoSuchElementException::new);
    }
}
