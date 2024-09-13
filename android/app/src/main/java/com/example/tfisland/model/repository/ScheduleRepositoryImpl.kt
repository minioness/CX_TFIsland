package com.example.tfisland.model.repository

import com.example.tfisland.model.dto.RecentScheduleResponse
import com.example.tfisland.model.dto.ScheduleResponse
import com.example.tfisland.model.network.ApiService

class ScheduleRepositoryImpl(private val apiService: ApiService) {

    suspend fun getSchedule(): ScheduleResponse? {
        return try {
            val response = apiService.getSchedule()
            if (response.isSuccessful) {
                response.body() // 성공 시 응답 본문 반환
            } else {
                throw Exception("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to fetch schedules: ${e.message}")
        }
    }

    suspend fun getScheduleRecent(): RecentScheduleResponse? {
        return try {
            val response = apiService.getScheduleRecent()
            if (response.isSuccessful) {
                response.body() // 성공 시 응답 본문 반환
            } else {
                throw Exception("Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            throw Exception("Failed to fetch recent schedule: ${e.message}")
        }
    }
}
