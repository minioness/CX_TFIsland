package com.example.tfisland.model.repository

import com.example.tfisland.model.dto.ArtistResponse
import com.example.tfisland.model.network.ApiService

class ArtistRepositoryImpl(private val apiService: ApiService) {

    suspend fun getArtist(): List<ArtistResponse>? {

        val response = apiService.getArtist()

        return if (response.code() == 200) {
            response.body()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }

    }

}