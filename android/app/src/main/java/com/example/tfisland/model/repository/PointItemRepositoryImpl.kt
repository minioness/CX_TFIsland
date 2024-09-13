package com.example.tfisland.model.repository

import com.example.tfisland.model.dto.PointItemResponse
import com.example.tfisland.model.network.ApiService

class PointItemRepositoryImpl(private val apiService: ApiService) {

    suspend fun getPointItem(): List<PointItemResponse>? {

        val response = apiService.getPointItem()

        return if (response.code() == 200) {
            response.body()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun postPointItem(pointItemId: Long): Void? {

        val response = apiService.postPointItem(pointItemId)

        return if (response.code() == 200) {
            null
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

}