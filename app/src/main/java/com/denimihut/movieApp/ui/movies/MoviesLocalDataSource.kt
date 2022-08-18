package com.denimihut.movieApp.ui.movies

import com.denimihut.movieApp.database.Database

class MoviesLocalDataSource(database: Database) {
    private val moviesDAO: MoviesDAO = database.movieAppDatabase.moviesDao()

    fun getAll() = moviesDAO.getAll()
    fun save(movie: Movies) = moviesDAO.save(movie)
    fun saveAll(movies: List<Movies>) = moviesDAO.saveAll(movies)
    fun delete(movie: Movies) = moviesDAO.delete(movie)
    fun deleteAll() = moviesDAO.deleteAll()
    fun deleteAll(movies: List<Movies>) = moviesDAO.deleteAll(movies)
    fun replaceAll(movies: List<Movies>) = moviesDAO.replaceAll(movies)
    fun getCount() = moviesDAO.getCount()
    fun getFavorite() = moviesDAO.getFavorite()
    fun getWatched() = moviesDAO.getWatched()
}