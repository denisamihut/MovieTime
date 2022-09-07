package com.denimihut.movieApp.save_movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denimihut.movieApp.R
import com.denimihut.movieApp.adapter.WatchedMoviesAdapter
import com.denimihut.movieApp.entities.Movies
import com.denimihut.movieApp.repository.MoviesRepository
import kotlinx.coroutines.*

class WatchedListFragment : Fragment(R.layout.fragment_watched_list) {
    private val movieRepository: MoviesRepository = MoviesRepository.instance
    private var movies: MutableList<Movies> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListOfMovies(view)
    }

    private fun setupRecyclerView(view: View) {
        val linearLayoutManager = LinearLayoutManager(view.context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_watched)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.reverseLayout = false

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = WatchedMoviesAdapter(movies)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initializeListOfMovies(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            movies = movieRepository.getWatched().toMutableList()
            withContext(Dispatchers.Main) {
                setupRecyclerView(view)
            }
        }
    }
}