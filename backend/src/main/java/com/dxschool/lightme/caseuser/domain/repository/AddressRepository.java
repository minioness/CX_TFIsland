package com.dxschool.lightme.caseuser.domain.repository;

import com.dxschool.lightme.caseuser.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByCityAndDistrictAndStreet(String city, String district, String street);
}
