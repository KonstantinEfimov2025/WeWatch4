package com.example.wewatch4.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String = "50ed6b7a" // Твой ключ
    ): MovieResponseDto
}