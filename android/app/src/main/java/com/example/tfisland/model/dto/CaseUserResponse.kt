package com.example.tfisland.model.dto

data class CaseUserResponse(
    val artist: String,
    val address: AddressResponse,
    val brightness: Int,
    val colorCode: String,
    val point: Long,
    val intro: String,
    val profileLink: String?,
    val nickname: String
)
