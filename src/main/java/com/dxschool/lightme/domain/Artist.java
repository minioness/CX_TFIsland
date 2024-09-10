package com.dxschool.lightme.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
public class Artist {
    @Id
    private Long artistId;

    private String artistName;

    @Column(length = 6)
    private String colorCode;

    private String company;
    private String logoLink;

    @ColumnDefault("0")
    private long score;
}
