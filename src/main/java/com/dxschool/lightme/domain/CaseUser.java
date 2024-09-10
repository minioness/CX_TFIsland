package com.dxschool.lightme.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseUserId;

    @Column(nullable = false)
    private String nickname;

    @ColumnDefault("0")
    private int brightness;

    @ColumnDefault("0")
    private int color;

    @ColumnDefault("0")
    private Long point;

    private String profileUrl;

    @Column(nullable = false)
    @ManyToOne
    private Artist themeArtist;

    @OneToMany
    @Builder.Default
    private List<Image> images = new ArrayList<>();

    @OneToMany
    @Builder.Default
    private List<Music> playlist = new ArrayList<>();

    @OneToMany
    @Builder.Default
    private List<Video> videos = new ArrayList<>();

    @ManyToOne
    private Address address;
}
