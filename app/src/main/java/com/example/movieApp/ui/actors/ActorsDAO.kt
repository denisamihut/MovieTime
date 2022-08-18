package com.example.movieApp.ui.actors

import androidx.room.*

@Dao
interface ActorsDAO {
    @Query("SELECT * FROM actors")
    fun getAll(): List<Actors>

    @Query("SELECT id FROM actors")
    fun getAllIds(): List<Int>

    @Insert
    fun save(actor: Actors)

    @Insert
    fun saveAll(actors: List<Actors>)

    @Delete
    fun delete(actor: Actors)

    @Delete
    fun deleteAll(actors: List<Actors>)

    @Query("DELETE FROM actors")
    fun deleteAll()

    @Transaction
    fun replaceAll(actors: List<Actors>) {
        deleteAll()
        saveAll(actors)
    }

    @Query("SELECT COUNT(id) FROM actors")
    fun getCount(): Int
}