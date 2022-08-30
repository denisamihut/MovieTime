package com.denimihut.movieApp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denimihut.movieApp.service_genre.Genres
import com.denimihut.movieApp.dao.GenresDAO
import com.denimihut.movieApp.service_actor.Actors
import com.denimihut.movieApp.dao.ActorsDAO
import com.denimihut.movieApp.service_movie.Movies
import com.denimihut.movieApp.dao.MoviesDAO

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



