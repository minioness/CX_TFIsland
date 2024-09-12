package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.caseuser.domain.CaseUser;

public record CaseUserResponse(
    String artist,
    int brightness,
    String colorCode,
    long point,
    String profileUrl,
    String nickname,
    AddressResponse address,
    String intro
) {
    public static CaseUserResponse from(CaseUser caseUser) {
        return new CaseUserResponse(
                caseUser.getThemeArtist().getArtistName(),
                caseUser.getBrightness(),
                caseUser.getColor(),
                caseUser.getPoint(),
                caseUser.getProfileLink(),
                caseUser.getNickname(),
                AddressResponse.from(caseUser.getAddress()),
                caseUser.getIntro()
        );
    }
}
