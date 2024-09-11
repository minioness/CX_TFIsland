package com.dxschool.lightme.caseuser.domain;

import com.dxschool.lightme.artist.domain.Artist;
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

    @ColumnDefault("FFFFFF")
    private String color;

    @ColumnDefault("0")
    private Long point;

    private String profileLink;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Artist themeArtist;

    @OneToMany
    @Builder.Default
    @JoinColumn(name = "case_user_id")
    private List<Image> images = new ArrayList<>();

    @OneToMany
    @Builder.Default
    @JoinColumn(name = "case_user_id")
    private List<Music> playlist = new ArrayList<>();

    @OneToMany
    @Builder.Default
    @JoinColumn(name = "case_user_id")
    private List<Video> videos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public void updateThemeArtist(Artist artist) {
        this.themeArtist = artist;
    }
}
