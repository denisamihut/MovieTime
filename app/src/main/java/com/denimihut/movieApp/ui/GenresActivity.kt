package com.denimihut.movieApp.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denimihut.movieApp.R
import com.denimihut.movieApp.adapter.GenresAdapter
import com.denimihut.movieApp.ui.genres.Genres
import com.denimihut.movieApp.ui.genres.GenresRepository
import kotlinx.coroutines.*

class GenresActivity : AppCompatActivity() {
    private var genres: List<Genres> = emptyList()
    private val genreRepository = GenresRepository.instance

    @OptIn(DelicateCoroutinesApi::class)
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
        setContentView(R.layout.activity_genres)
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genreRepository.deleteAllLocal()
            genreRepository.saveAllLocal(getSelectedGenres())
        }
        OnBoardingActivity.open(this)
    }

    private fun setupRecyclerView() {
        val rvGenres = findViewById<RecyclerView>(R.id.rvGenres)
        rvGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenres.adapter = GenresAdapter(genres)

        val gridLayoutManager =
            GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        rvGenres.layoutManager = gridLayoutManager
    }

    @OptIn(DelicateCoroutinesApi::class)
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