package com.dxschool.lightme.mission.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"caseUserId", "missionId"})
public class MissionId {
    private Long caseUserId;
    private Long missionId;
}
