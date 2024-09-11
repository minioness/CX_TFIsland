package com.dxschool.lightme.schedule.domain.repository;

import com.dxschool.lightme.schedule.domain.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
    List<UserSchedule> findByUser_CaseUserId(Long id);
}
