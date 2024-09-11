package com.dxschool.lightme.schedule.domain;

import com.dxschool.lightme.artist.domain.Artist;
import com.dxschool.lightme.common.domain.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArtistSchedule {
    @Id
    @GeneratedValue
    private Long artistScheduleId;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    @Column(scale = 7, precision = 10)
    private BigDecimal latitude;
    @Column(scale = 7, precision = 10)
    private BigDecimal longitude;

    @ManyToOne
    private Artist artist;
}
