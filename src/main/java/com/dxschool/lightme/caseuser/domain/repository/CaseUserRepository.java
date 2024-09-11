package com.dxschool.lightme.caseuser.domain.repository;

import com.dxschool.lightme.caseuser.domain.CaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseUserRepository extends JpaRepository<CaseUser, Long> {
}
