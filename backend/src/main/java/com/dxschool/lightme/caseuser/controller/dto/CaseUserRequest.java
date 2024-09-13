package com.dxschool.lightme.caseuser.controller.dto;

public record CaseUserRequest(
    Long artistId,
    String nickname,
    String address,
    String profileLink,
    String intro
) {

}
