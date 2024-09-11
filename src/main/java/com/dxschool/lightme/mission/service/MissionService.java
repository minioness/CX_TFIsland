package com.dxschool.lightme.mission.service;

import com.dxschool.lightme.caseuser.controller.User;
import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.caseuser.domain.repository.CaseUserRepository;
import com.dxschool.lightme.mission.controller.dto.MissionResponse;
import com.dxschool.lightme.mission.domain.MissionId;
import com.dxschool.lightme.mission.domain.MissionLog;
import com.dxschool.lightme.mission.domain.MissionStatus;
import com.dxschool.lightme.mission.domain.repository.MissionLogRepository;
import com.dxschool.lightme.mission.domain.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.dxschool.lightme.mission.domain.MissionStatus.*;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionLogRepository missionLogRepository;
    private final CaseUserRepository caseUserRepository;

    private Map<Long, MissionStatus> getMissionStatus(Long userId) {
        return missionLogRepository.findAllByMissionLogId_CaseUserId(userId).stream()
                .collect(Collectors.toMap(
                        log -> log.getMissionLogId().getMissionId(),
                        MissionLog::getStatus
                ));
    }

    public List<MissionResponse> findAll(@User Long userId) {
        CaseUser caseUser = caseUserRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);
        Long themeArtistId = caseUser.getThemeArtist().getArtistId();

        Map<Long, MissionStatus> myMissionStatus = getMissionStatus(userId);

        return missionRepository.findAllByArtist_ArtistId(themeArtistId).stream()
                .map(mission -> {
                    MissionStatus status = myMissionStatus.getOrDefault(mission.getMissionId(), NOT_STARTED);
                    return MissionResponse.from(mission, status);
                })
                .toList();
    }

    @Transactional
    public void accept(Long userId, Long missionId) {
        MissionLog acceptedMission = MissionLog.builder()
                .missionLogId(new MissionId(userId, missionId))
                .status(IN_PROGRESS)
                .build();
        missionLogRepository.save(acceptedMission);
    }

    @Transactional
    public void complete(Long userId, Long missionId) {
        MissionLog completedMission = missionLogRepository.findAllByMissionLogId_CaseUserId(userId).stream()
                .filter(log -> log.getMissionLogId().getMissionId().equals(missionId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        completedMission.setStatus(ACCOMPLISHED);
    }
}
