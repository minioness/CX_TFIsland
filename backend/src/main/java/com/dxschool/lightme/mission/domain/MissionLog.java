package com.dxschool.lightme.mission.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MissionLog {
    @EmbeddedId
    private MissionId missionLogId;

    @Enumerated(EnumType.STRING)
    @Setter
    private MissionStatus status = MissionStatus.NOT_STARTED;
}
