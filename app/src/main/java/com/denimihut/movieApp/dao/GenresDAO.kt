package com.denimihut.movieApp.service_genre

import androidx.room.*

@Dao
interface GenresDAO {

    @Query("SELECT * FROM genres")
    fun getAll(): List<Genres>

    @Query("SELECT id FROM genres")
    fun getAllIds(): List<Int>

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

    @Query("SELECT COUNT(id) FROM genres")
    fun getCount(): Int
}