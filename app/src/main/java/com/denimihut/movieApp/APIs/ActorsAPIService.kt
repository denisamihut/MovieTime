package com.denimihut.movieApp.service_actor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActorsAPIService {
    @GET("person/popular")
    fun getActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Call<ActorsListResponse>

    @GET("search/person")
    fun getSearchedActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<ActorsListResponse>
}