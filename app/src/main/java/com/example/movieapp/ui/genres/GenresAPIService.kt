package com.example.movieapp.ui.genres

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresAPIService {

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("Language") language: String
    ): Call<GenresListResponse>
}