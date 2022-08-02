package com.example.movieapp.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.on_boarding.OnBoardingActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesActivity : AppCompatActivity() {

    private var movies: List<Movies> = emptyList()
    private val movieRepository = MoviesRepository.instance

    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            movies = movieRepository.getAllRemoteMovies()
            withContext(Dispatchers.Main) {
                preselectSavedMovies()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_search_movies)
        setOnClickListeners()
        getMovies()
    }

    private fun setOnClickListeners() {
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            saveMovies()
        }
    }

    private fun getSelectedMovies(): List<Movies> {
        return movies.filter { movie -> movie.isSelected }
    }

    private fun saveMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            movieRepository.deleteAllLocal()
            movieRepository.saveAllLocal(getSelectedMovies())
        }
        OnBoardingActivity.open(this)
    }

    private fun setupRecyclerView() {
        val rvMovie = findViewById<RecyclerView>(R.id.rvMovies)
        rvMovie.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMovie.adapter = MoviesAdapter(movies)
    }

    private fun preselectSavedMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedMovies: List<Movies> = movieRepository.getAllLocalMovies()
            withContext(Dispatchers.Main) {
                movies.forEach { movie -> movie.isSelected = savedMovies.contains(movie) }
                setupRecyclerView()
            }
        }
    }
}