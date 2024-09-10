package com.dxschool.lightme.domain;

import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;

@Embeddable
@RequiredArgsConstructor
public class MissionId {
    private Long caseUserId;
    private Long missionId;
}
