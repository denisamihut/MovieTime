package com.denimihut.movieApp.remote

import com.denimihut.movieApp.APIs.GenresAPIService
import com.denimihut.movieApp.entities.Genres
import com.denimihut.movieApp.mapper.GenresMapper
import com.denimihut.movieApp.network.executeAndDeliver
import com.denimihut.movieApp.utils.Constants.API_KEY
import com.denimihut.movieApp.utils.Constants.LANGUAGE
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