package com.dxschool.lightme.artist.domain.repository;

import com.dxschool.lightme.artist.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
