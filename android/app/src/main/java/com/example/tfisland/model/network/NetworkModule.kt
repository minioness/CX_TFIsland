package com.example.tfisland.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    // Lazy initialization ensures that the Retrofit instance is created only when it's needed
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.89:8081") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create()) // JSON 변환기 추가
            .build()
    }

    // ApiService 제공
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
