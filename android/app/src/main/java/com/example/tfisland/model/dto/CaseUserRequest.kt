package com.example.tfisland.model.dto

data class CaseUserRequest(
    val artistId: Long,
    val nickname: String,
    val intro: String,
    val address: String,
    val priofileLink: String
)
