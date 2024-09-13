package com.example.tfisland.model.network

import com.example.tfisland.model.dto.ArtistResponse
import com.example.tfisland.model.dto.CaseUserDetailResponse
import com.example.tfisland.model.dto.CaseUserRequest
import com.example.tfisland.model.dto.CaseUserResponse
import com.example.tfisland.model.dto.CaseUserUpdateRequest
import com.example.tfisland.model.dto.MemoryRequest
import com.example.tfisland.model.dto.MemoryResponse
import com.example.tfisland.model.dto.MissionResponse
import com.example.tfisland.model.dto.PointItemResponse
import com.example.tfisland.model.dto.RecentScheduleResponse
import com.example.tfisland.model.dto.ScheduleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    /*
    * 응원봉 유저 관련
    */
    @GET("/api/users")
    suspend fun getCaseUser(): Response<CaseUserResponse>

    @GET("/api/users/detail")
    suspend fun getCaseUserDetail(): Response<CaseUserDetailResponse>

    @POST("/api/users")
    suspend fun postCaseUser(
        @Body caseUser: CaseUserRequest
    ): Response<Void>

    @PATCH("/api/users")
    suspend fun patchCaseUser(
        @Body caseUser: CaseUserUpdateRequest
    ): Response<Void>

    @GET("/api/users/near")
    suspend fun getCaseUserByNear(): Response<List<CaseUserResponse>>

    /*
    * 아티스트 관련
    */
    @GET("/api/artists")
    suspend fun getArtist(): Response<List<ArtistResponse>>

    /*
    * 일정 관련
    */
    @GET("/api/schedules")
    suspend fun getSchedule(): Response<ScheduleResponse>

    @GET("/api/schedules/recent")
    suspend fun getScheduleRecent(): Response<RecentScheduleResponse>

    /*
    * 미션 관련
    */
    @GET("/api/missions")
    suspend fun getMission(): Response<List<MissionResponse>>

    @POST("/api/missions/{missionId}")
    suspend fun postMission(
        @Path("missionId") missionId: Long
    ): Response<Void>

    @DELETE("/api/missions/{missionId}")
    suspend fun deleteMission(
        @Path("missionId") missionId: Long
    ): Response<Void>

    /*
    * 추억 관련
    */
    @GET("/api/memories")
    suspend fun getMemory(): Response<List<MemoryResponse>>

    @POST("/api/memories")
    suspend fun postMemory(
        @Body memory: MemoryRequest
    ): Response<Void>

    /*
    * 포인트 관련
    */
    @GET("/api/items")
    suspend fun getPointItem(): Response<List<PointItemResponse>>

    @POST("/api/items/{pointItemId}")
    suspend fun postPointItem(
        @Path("pointItemId") pointItemId: Long
    ): Response<Void>
}