package com.dxschool.lightme.caseuser.controller.dto;

public record CaseUserUpdateRequest(
        Long artistId,
        String nickname,
        String address,
        String intro
) {
}
