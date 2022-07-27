package com.example.movieapp.ui.genres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.ui.onBoardingScreen.OnBoardingScreenActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GenresActivity : AppCompatActivity() {

    private var genres: List<Genres> = emptyList()
    private val genreRepository = GenresRepository.instance

    private fun getGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genreRepository.getAllRemoteGenres()
            withContext(Dispatchers.Main) {
                preselectSavedGenres()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres_screen)
        setOnClickListeners()
        getGenres()
    }

    private fun setOnClickListeners() {
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            saveGenres()
        }
    }

    private fun getSelectedGenres(): List<Genres> {
        return genres.filter { genre -> genre.isSelected }
    }

    private fun saveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genreRepository.deleteAllLocal()
            genreRepository.saveAllLocal(getSelectedGenres())
        }
        OnBoardingScreenActivity.open(this)
    }

    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rv_movies)
        rvGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = GenresAdapter(genres)
    }

    private fun preselectSavedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenres: List<Genres> = genreRepository.getAllLocalGenres()
            withContext(Dispatchers.Main) {
                genres.forEach { genre -> genre.isSelected = savedGenres.contains(genre) }
                setupRecyclerView()
            }
        }
    }
}