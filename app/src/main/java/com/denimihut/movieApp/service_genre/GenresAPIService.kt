package com.denimihut.movieApp.ui.genres

import com.denimihut.movieApp.ui.movies.MoviesListResponse
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