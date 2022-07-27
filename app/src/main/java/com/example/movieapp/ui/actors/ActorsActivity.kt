package com.example.movieapp.ui.actors

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

class ActorsActivity : AppCompatActivity() {

    private var actors: List<Actors> = emptyList()
    private val actorRepository = ActorsRepository.instance

    private fun getActors() {
        GlobalScope.launch(Dispatchers.IO) {
            actors = actorRepository.getAllRemoteActors()
            withContext(Dispatchers.Main) {
                preselectSavedActors()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_screen)
        setOnClickListeners()
        getActors()
    }

    private fun setOnClickListeners() {
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener {
            saveActors()
        }
    }

    private fun getSelectedActors(): List<Actors> {
        return actors.filter { actor -> actor.isSelected }
    }

    private fun saveActors() {
        GlobalScope.launch(Dispatchers.IO) {
            actorRepository.deleteAllLocal()
            actorRepository.saveAllLocal(getSelectedActors())
        }
        OnBoardingScreenActivity.open(this)
    }

    private fun setupRecyclerView() {
        val rvActor = findViewById<RecyclerView>(R.id.rv_actors)
        rvActor.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvActor.adapter = ActorsAdapter(actors)
    }

    private fun preselectSavedActors() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedActors: List<Actors> = actorRepository.getAllLocalActors()
            withContext(Dispatchers.Main) {
                actors.forEach { actor -> actor.isSelected = savedActors.contains(actor) }
                setupRecyclerView()
            }
        }
    }
}