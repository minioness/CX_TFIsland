package com.dxschool.lightme.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MissionLog {
    @EmbeddedId
    private MissionId missionLogId;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("NOT_STARTED")
    private MissionStatus status;
}
