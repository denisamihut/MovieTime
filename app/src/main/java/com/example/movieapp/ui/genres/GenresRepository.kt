package com.example.movieapp.ui.genres

import com.example.movieapp.network.APIClient

class GenresRepository private constructor() {
    companion object {
        val instance = GenresRepository()
    }

    private val GenresRemoteDataSource = com.example.movieapp.ui.genres.GenresRemoteDataSource(APIClient.instance.retrofit)

    fun getAllRemoteGenres() = GenresRemoteDataSource.getGenres()
}