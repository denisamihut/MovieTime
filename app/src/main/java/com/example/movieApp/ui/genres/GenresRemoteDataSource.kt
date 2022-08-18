package com.example.movieApp.ui.genres

import com.example.movieApp.network.executeAndDeliver
import com.example.movieApp.utils.Constants.API_KEY
import com.example.movieApp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class GenresRemoteDataSource(retrofit: Retrofit) {
    private val apiService: GenresAPIService = retrofit.create(GenresAPIService::class.java)
    private val genreMapper = GenresMapper()

    fun getGenres(): List<Genres> {
        return apiService.getGenres(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .genres
            .map { genreMapper.map(it) }
    }
}