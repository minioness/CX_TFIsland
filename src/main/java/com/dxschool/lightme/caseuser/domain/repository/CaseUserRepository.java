package com.dxschool.lightme.caseuser.domain.repository;

import com.dxschool.lightme.caseuser.domain.CaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseUserRepository extends JpaRepository<CaseUser, Long> {
    List<CaseUser> findTop20ByAddress_AddressId(Long addressId);
}
