package com.dxschool.lightme.artist.service;

import com.dxschool.lightme.artist.controller.dto.ArtistResponse;
import com.dxschool.lightme.artist.domain.Artist;
import com.dxschool.lightme.artist.domain.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ArtistServiceTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistService artistService;

    @Test
    void 아티스트_목록_조회() {
        //given
        artistRepository.save(Artist.builder().artistId(1L).artistName("세븐틴").logo("logo").color("FFFFFF").company("SM Entertainment").build());
        artistRepository.save(Artist.builder().artistId(2L).artistName("뉴진스").logo("logo").color("000000").company("Hybe").build());
        //when
        List<ArtistResponse> artists = artistService.findAll();
        //then
        assertThat(artists).hasSize(2);
        assertThat(artists.getFirst().artistId()).isEqualTo(1L);
        assertThat(artists.getFirst().artistName()).isEqualTo("세븐틴");
    }
}
