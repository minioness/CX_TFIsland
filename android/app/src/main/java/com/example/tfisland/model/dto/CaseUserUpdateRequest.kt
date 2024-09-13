package com.example.tfisland.model.dto

data class CaseUserUpdateRequest(
    val artistId: Long,
    val nickname: String,
    val intro: String,
    val address: String
)
