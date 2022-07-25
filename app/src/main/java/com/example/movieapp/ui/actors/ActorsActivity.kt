package com.example.movieapp.ui.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsActivity : AppCompatActivity() {

    private var results: List<Actors> = emptyList()
    private val actorsRepository = ActorsRepository.instance

    private fun getActors() {
        GlobalScope.launch(Dispatchers.IO) {
            results = actorsRepository.getAllRemoteActors()
            withContext(Dispatchers.Main) {
                setupRecyclerView()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_screen)
        getActors()
    }

    private fun setupRecyclerView() {
        val rvActor = findViewById<RecyclerView>(R.id.rv_actors)
        rvActor.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvActor.adapter = ActorsAdapter(results)
    }
}