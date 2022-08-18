package com.denimihut.movieApp.ui.genres

import com.denimihut.movieApp.database.Database

class GenresLocalDataSource(database: Database) {
    private val genresDAO: GenresDAO = database.movieAppDatabase.genresDao()

    fun getAll() = genresDAO.getAll()
    fun getAllIds() = genresDAO.getAllIds()
    fun save(genre: Genres) = genresDAO.save(genre)
    fun saveAll(genres: List<Genres>) = genresDAO.saveAll(genres)
    fun delete(genre: Genres) = genresDAO.delete(genre)
    fun deleteAll() = genresDAO.deleteAll()
    fun deleteAll(genres: List<Genres>) = genresDAO.deleteAll(genres)
    fun replaceAll(genres: List<Genres>) = genresDAO.replaceAll(genres)
    fun getCount() = genresDAO.getCount()
}