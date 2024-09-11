package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.caseuser.domain.CaseUser;

public record CaseUserResponse(
    Long artistId,
    int brightness,
    String colorCode,
    long point,
    String profileUrl,
    String nickname,
    String address
) {
    public static CaseUserResponse from(CaseUser caseUser) {
        return new CaseUserResponse(
                caseUser.getThemeArtist().getArtistId(),
                caseUser.getBrightness(),
                caseUser.getColor(),
                caseUser.getPoint(),
                caseUser.getProfileLink(),
                caseUser.getNickname(),
                caseUser.getAddress().toString()
        );
    }
}
