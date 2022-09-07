package com.denimihut.movieApp.APIs

import com.denimihut.movieApp.response.GenresListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresAPIService {
    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<GenresListResponse>
}