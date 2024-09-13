package com.example.tfisland.model.repository

import com.example.tfisland.model.dto.MissionResponse
import com.example.tfisland.model.network.ApiService

class MissionRepositoryImpl(private val apiService: ApiService) {

    suspend fun getMission(): List<MissionResponse>? {

        val response = apiService.getMission()

        return if (response.code() == 200) {
            response.body()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun postMission(missionId: Long): Void? {

        val response = apiService.postMission(missionId)

        return if (response.code() == 200) {
            null
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun deleteMission(missionId: Long): Void? {

        val response = apiService.deleteMission(missionId)

        return if (response.code() == 200) {
            null
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

}