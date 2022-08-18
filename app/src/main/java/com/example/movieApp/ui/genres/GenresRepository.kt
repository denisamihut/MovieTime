package com.example.movieApp.ui.genres

import com.example.movieApp.database.Database
import com.example.movieApp.network.APIClient

class GenresRepository private constructor() {
    companion object {
        val instance = GenresRepository()
    }

    private val genresRemoteDataSource = GenresRemoteDataSource(APIClient.instance.retrofit)
    private val genresLocalDataSource = GenresLocalDataSource(Database.instance)

    fun getAllRemoteGenres() = genresRemoteDataSource.getGenres()

    fun getAllLocalIds() = genresLocalDataSource.getAllIds()
    fun getAllLocalGenres() = genresLocalDataSource.getAll()
    fun saveAllLocal(genres: List<Genres>) = genresLocalDataSource.saveAll(genres)
    fun deleteAllLocal() = genresLocalDataSource.deleteAll()
    fun getCount() = genresLocalDataSource.getCount()
//    fun saveLocal(genre: Genres) = genresLocalDataSource.save(genre)
//    fun deleteLocal(genre: Genres) = genresLocalDataSource.delete(genre)
//    fun deleteAllLocal(genres: List<Genres>) = genresLocalDataSource.deleteAll(genres)
//    fun replaceAllLocal(genres: List<Genres>) = genresLocalDataSource.replaceAll(genres)
}