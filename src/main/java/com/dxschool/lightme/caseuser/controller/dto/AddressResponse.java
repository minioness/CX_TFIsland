package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.caseuser.domain.Address;

public record AddressResponse(
        String city,
        String district,
        String street
) {
    public static AddressResponse from(Address address) {
        return new AddressResponse(address.getCity(), address.getDistrict(), address.getStreet());
    }
}
