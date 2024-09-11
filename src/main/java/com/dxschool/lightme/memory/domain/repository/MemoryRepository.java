package com.dxschool.lightme.memory.domain.repository;

import com.dxschool.lightme.memory.domain.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    List<Memory> findByCreatedBy_CaseUserId(Long userId);
}
