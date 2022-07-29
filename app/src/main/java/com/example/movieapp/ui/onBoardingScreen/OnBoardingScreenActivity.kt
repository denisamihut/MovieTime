package com.example.movieapp.ui.onBoardingScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movieapp.R
import com.example.movieapp.ui.actors.ActorsActivity
import com.example.movieapp.ui.genres.GenresActivity
import com.example.movieapp.ui.movies.MoviesActivity

class OnBoardingScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)
        setClickListeners()
    }

    private fun setClickListeners() {
        val genresButton = findViewById<Button>(R.id.btnGenres)
        genresButton.setOnClickListener {
            startActivity(Intent(this, GenresActivity::class.java))
        }
        val actorsButton = findViewById<Button>(R.id.btnActors)
        actorsButton.setOnClickListener {
            startActivity(Intent(this, ActorsActivity::class.java))
        }
//        val moviesButton = findViewById<Button>(R.id.btnMovies)
//        moviesButton.setOnClickListener {
//            startActivity(Intent(this, MoviesActivity::class.java))
//        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, OnBoardingScreenActivity::class.java))
        }
    }
}