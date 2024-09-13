package com.dxschool.lightme.artist.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Artist {
    @Id
    private Long artistId;

    private String artistName;

    @Column(length = 6)
    private String color;

    private String company;
    private String logo;

    @ColumnDefault("0")
    private long score;

}
