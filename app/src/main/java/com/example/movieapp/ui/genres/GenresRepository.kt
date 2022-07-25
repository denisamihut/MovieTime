package com.example.movieapp.ui.genres

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient

class GenresRepository private constructor() {
    companion object {
        val instance = GenresRepository()
    }

    private val GenresRemoteDataSource = GenresRemoteDataSource(APIClient.instance.retrofit)
    private val genresLocalDataSource = GenresLocalDataSource(Database.instance)

    fun getAllRemoteGenres() = GenresRemoteDataSource.getGenres()

    fun getAllLocalGenres() = genresLocalDataSource.getAll()
    fun saveLocal(genre: Genres) = genresLocalDataSource.save(genre)
    fun saveAllLocal(genres: List<Genres>) = genresLocalDataSource.saveAll(genres)
    fun deleteLocal(genre: Genres) = genresLocalDataSource.delete(genre)
    fun deleteAllLocal() = genresLocalDataSource.deleteAll()
    fun deleteAllLocal(genres: List<Genres>) = genresLocalDataSource.deleteAll(genres)
    fun replaceAllLocal(genres: List<Genres>) = genresLocalDataSource.replaceAll(genres)
}