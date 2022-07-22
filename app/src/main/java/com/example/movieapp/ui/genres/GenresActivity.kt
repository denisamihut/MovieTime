package com.example.movieapp.ui.genres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresActivity : AppCompatActivity() {

    private var genres: List<Genres> = emptyList()
    private val genresRepository = GenresRepository.instance

    private fun getGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genresRepository.getAllRemoteGenres()
            withContext(Dispatchers.Main) {
                setupRecyclerView()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres_screen)
        getGenres()
    }

    private fun setupRecyclerView() {
        val rvGenre = findViewById<RecyclerView>(R.id.rv_genres)
        rvGenre.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGenre.adapter = GenresAdapter(genres)
    }
}