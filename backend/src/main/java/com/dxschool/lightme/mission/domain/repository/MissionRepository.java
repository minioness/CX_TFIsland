package com.dxschool.lightme.mission.domain.repository;

import com.dxschool.lightme.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByArtist_ArtistId(Long artistId);
}
