package com.denimihut.movieApp.repository

import com.denimihut.movieApp.database.Database
import com.denimihut.movieApp.network.APIClient
import com.denimihut.movieApp.entities.Movies
import com.denimihut.movieApp.local.MoviesLocalDataSource
import com.denimihut.movieApp.remote.MoviesRemoteDataSource

class MoviesRepository private constructor() {
    companion object {
        val instance = MoviesRepository()
    }

    private val moviesRemoteDataSource = MoviesRemoteDataSource(APIClient.instance.retrofit)
    private val moviesLocalDataSource = MoviesLocalDataSource(Database.instance)

    fun getAllRemoteMovies(withCast: String, withGenres: String): List<Movies> {
        return moviesRemoteDataSource.getMovies(withCast, withGenres)
    }

    fun getAllSearchedMovies(query: String) = moviesRemoteDataSource.getSearchedMovies(query)

    fun getAllLocalMovies() = moviesLocalDataSource.getAll()
    fun deleteLocal(movie: Movies) = moviesLocalDataSource.delete(movie)
    fun replaceAllLocal(movies: List<Movies>) = moviesLocalDataSource.replaceAll(movies)
    fun getCount() = moviesLocalDataSource.getCount()
    fun getFavorite() = moviesLocalDataSource.getFavorite()
    fun getWatched() = moviesLocalDataSource.getWatched()
//    fun getSearchedMovies(query: String) = moviesRemoteDataSource.getSearchedMovies(query)
//    fun saveLocal(movie: Movies) = moviesLocalDataSource.save(movie)
//    fun saveAllLocal(movies: List<Movies>) = moviesLocalDataSource.saveAll(movies)
//    fun deleteAllLocal() = moviesLocalDataSource.deleteAll()
//    fun deleteAllLocal(movies: List<Movies>) = moviesLocalDataSource.deleteAll(movies)
}