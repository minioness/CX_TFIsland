package com.example.tfisland.model.dto

data class ArtistResponse(
    val artistId: Long,
    val name: String,
    val color: String?,
    val company: String,
    val logo: String?,
    val score: Int
)
