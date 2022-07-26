package com.example.movieapp.ui.actors

import androidx.room.*
import com.example.movieapp.ui.genres.Genres

@Dao
interface ActorsDAO {
    @Query("SELECT * FROM results")
    fun getAll(): List<Actors>

    @Insert
    fun save(actor: Actors)

    @Insert
    fun saveAll(actors: List<Actors>)

    @Delete
    fun delete(actor: Actors)

    @Delete
    fun deleteAll(actors: List<Actors>)

    @Query("DELETE FROM results")
    fun deleteAll()

    @Transaction
    fun replaceAll(actors: List<Actors>) {
        deleteAll()
        saveAll(actors)
    }
}