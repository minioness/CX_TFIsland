package com.dxschool.lightme.schedule.domain.repository;

import com.dxschool.lightme.schedule.domain.ArtistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistScheduleRepository extends JpaRepository<ArtistSchedule, Long> {
    List<ArtistSchedule> findAllByArtist_ArtistId(Long artistId);
}
