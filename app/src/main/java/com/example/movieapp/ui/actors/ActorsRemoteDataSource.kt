package com.example.movieapp.ui.actors

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constants.API_KEY
import com.example.movieapp.utils.Constants.LANGUAGE
import com.example.movieapp.utils.Constants.PAGE
import retrofit2.Retrofit

class ActorsRemoteDataSource(retrofit: Retrofit) {
    private val apiService: ActorsAPIService = retrofit.create(ActorsAPIService::class.java)
    private val actorMapper = ActorsMapper()

    fun getActors(): List<Results> {
        return apiService.getActors(API_KEY, LANGUAGE, PAGE)
            .executeAndDeliver()
            .results
            .map { actorMapper.map(it) }
    }
}