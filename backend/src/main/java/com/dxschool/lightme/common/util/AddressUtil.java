package com.dxschool.lightme.common.util;

import com.dxschool.lightme.caseuser.domain.Address;

public class AddressUtil {
    public static Address parseAddress(String address) {
        String[] tokens = address.split(" ");

        return Address.builder()
                .city(tokens[0])
                .district(tokens[1])
                .street(tokens[2])
                .build();
    }
}
