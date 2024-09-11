package com.dxschool.lightme.memory.domain;

import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Memory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoryId;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String color;

    private LocalDate memoryDate;

    @ManyToOne
    private CaseUser createdBy;
}
