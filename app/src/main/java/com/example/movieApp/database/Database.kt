package com.example.movieApp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieApp.ui.genres.Genres
import com.example.movieApp.ui.genres.GenresDAO
import com.example.movieApp.ui.actors.Actors
import com.example.movieApp.ui.actors.ActorsDAO
import com.example.movieApp.ui.movies.Movies
import com.example.movieApp.ui.movies.MoviesDAO

class Database private constructor() {
    companion object {
        val instance = Database()
    }

    @androidx.room.Database(
        entities = [Genres::class, Actors::class, Movies::class],
        version = 6
    )

    abstract class MovieAppDatabase : RoomDatabase() {
        abstract fun genresDao(): GenresDAO
        abstract fun actorsDao(): ActorsDAO
        abstract fun moviesDao(): MoviesDAO
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



