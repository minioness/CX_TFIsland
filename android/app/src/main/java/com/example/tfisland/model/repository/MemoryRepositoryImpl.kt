package com.example.tfisland.model.repository

import android.util.Log
import com.example.tfisland.model.dto.MemoryRequest
import com.example.tfisland.model.dto.MemoryResponse
import com.example.tfisland.model.network.ApiService

class MemoryRepositoryImpl(private val apiService: ApiService) {

    suspend fun getMemory(): List<MemoryResponse>? {

        val response = apiService.getMemory()
        Log.d("SAdsdsadsadsa", response.body()?.get(0)?.memoryDate.toString())

        return if (response.code() == 200) {
            response.body()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun postMemory(memoryRequest: MemoryRequest): Void? {

        val response = apiService.postMemory(memoryRequest)

        return if (response.code() == 200) {
            null
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

}