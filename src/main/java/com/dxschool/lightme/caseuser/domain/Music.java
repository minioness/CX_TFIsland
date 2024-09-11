package com.dxschool.lightme.caseuser.domain;

import com.dxschool.lightme.artist.domain.Artist;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long musicId;

    @ManyToOne
    private Artist artist;

    private String title;
}
