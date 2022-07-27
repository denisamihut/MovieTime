package com.example.movieapp.ui.movies

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.utils.Constants.API_KEY
import com.example.movieapp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MoviesRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MoviesAPIService = retrofit.create(MoviesAPIService::class.java)
    private val movieMapper = MoviesMapper()

    fun getMovies(): List<Movies> {
        return apiService.getMovies(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }
}