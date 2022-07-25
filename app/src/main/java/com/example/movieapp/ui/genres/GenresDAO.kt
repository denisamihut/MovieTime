package com.example.movieapp.ui.genres

import androidx.room.*

@Dao
interface GenresDAO {

    @Query("SELECT * FROM genres")
    fun getAll(): List<Genres>

    @Insert
    fun save(genre: Genres)

    @Insert
    fun saveAll(genres: List<Genres>)

    @Delete
    fun delete(genre: Genres)

    @Delete
    fun deleteAll(genres: List<Genres>)

    @Query("DELETE FROM genres")
    fun deleteAll()

    @Transaction
    fun replaceAll(genres: List<Genres>) {
        deleteAll()
        saveAll(genres)
    }
}