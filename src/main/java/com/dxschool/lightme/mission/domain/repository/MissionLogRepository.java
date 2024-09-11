package com.dxschool.lightme.mission.domain.repository;

import com.dxschool.lightme.mission.domain.MissionId;
import com.dxschool.lightme.mission.domain.MissionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionLogRepository extends JpaRepository<MissionLog, MissionId> {
    List<MissionLog> findAllByMissionLogId_CaseUserId(Long caseUserId);
}
