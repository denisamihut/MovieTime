package com.example.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.ui.genres.Genres
import com.example.movieapp.ui.genres.GenresDAO

class Database private constructor() {
    companion object {
        val instance = Database()
    }

    @androidx.room.Database(
        entities = [Genres::class],
        version = 1
    )

    abstract class MovieAppDatabase : RoomDatabase() {
        abstract fun genresDao(): GenresDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
        private set

    fun initialize(context: Context) {
        this.movieAppDatabase = Room.databaseBuilder(
            context,
            MovieAppDatabase::class.java,
            "movie_app.db"
        ).fallbackToDestructiveMigration().build()

    }
}