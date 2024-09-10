package com.dxschool.lightme.domain;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Category {
    BIRTHDAY_CAFE("생일 카페"),
    BIRTHDAY("생일"),
    CONCERT("콘서트"),
    FAN_SIGN("팬싸인회"),
    POPUP_STORE("팝업스토어");
    //TODO : 추후 추가하기

    private final String description;

    public static Category from(String category) {
        return Arrays.stream(values())
                .filter(c -> c.description.equals(category))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
