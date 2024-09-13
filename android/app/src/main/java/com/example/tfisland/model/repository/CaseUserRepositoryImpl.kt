package com.example.tfisland.model.repository

import com.example.tfisland.model.dto.CaseUserDetailResponse
import com.example.tfisland.model.dto.CaseUserRequest
import com.example.tfisland.model.dto.CaseUserResponse
import com.example.tfisland.model.dto.CaseUserUpdateRequest
import com.example.tfisland.model.network.ApiService

open class CaseUserRepositoryImpl(private val apiService: ApiService)  {

    suspend fun getCaseUser(): CaseUserResponse? {

        val response = apiService.getCaseUser()

        return if (response.code() == 200) {
            response.body()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun getCaseUserDetail(): CaseUserDetailResponse? {

        val response = apiService.getCaseUserDetail()

        return if (response.code() == 200) {
            response.body()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun postCaseUser(caseUserRequest: CaseUserRequest): Void? {

        val response = apiService.postCaseUser(caseUserRequest)

        return if (response.code() == 200) {
            null
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }
    }

    suspend fun patchCaseUser(caseUserUpdateRequest: CaseUserUpdateRequest): Void? {

        val response = apiService.patchCaseUser(caseUserUpdateRequest)

        return if (response.code() == 204) {
            null
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }

    }

    suspend fun getCaseUserByNear(): List<CaseUserResponse>? {

        val response = apiService.getCaseUserByNear()

        return if (response.code() == 200) {
            response.body()
        } else {
            throw Exception("Error: ${response.code()} - ${response.message()}")
        }

    }

}