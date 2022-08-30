package com.denimihut.movieApp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denimihut.movieApp.R
import com.denimihut.movieApp.service_actor.Actors
import com.denimihut.movieApp.adapter.ActorsAdapter
import com.denimihut.movieApp.repository.ActorsRepository
import kotlinx.coroutines.*

class ActorsActivity : AppCompatActivity() {
    private var actors: List<Actors> = emptyList()
    private val actorRepository = ActorsRepository.instance

    @OptIn(DelicateCoroutinesApi::class)
    private fun getActors() {
        GlobalScope.launch(Dispatchers.IO) {
            actors = actorRepository.getAllRemoteActors()
            withContext(Dispatchers.Main) {
                preselectSavedActors()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getSearchedActors(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            actors = actorRepository.getAllSearchedActors(query)
            withContext(Dispatchers.Main) {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors)
        setOnClickListeners()
        getActors()
        setSearchTextListener()
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveActors() {
        GlobalScope.launch(Dispatchers.IO) {
            actorRepository.deleteAllLocal()
            actorRepository.saveAllLocal(getSelectedActors())
        }
        OnBoardingActivity.open(this)
    }

    private fun setupRecyclerView() {
        val rvActor = findViewById<RecyclerView>(R.id.rvActors)
        rvActor.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvActor.adapter = ActorsAdapter(actors)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun preselectSavedActors() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedActors: List<Actors> = actorRepository.getAllLocalActors()
            withContext(Dispatchers.Main) {
                actors.forEach { actor -> actor.isSelected = savedActors.contains(actor) }
                setupRecyclerView()
            }
        }
    }

    private fun setSearchTextListener() {
        val search = findViewById<SearchView>(R.id.svActors)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if ((newText?.length ?: 0) >= 1) {
                    getSearchedActors(newText ?: "")
                } else
                    getActors()
                return false
            }
        })
    }
}