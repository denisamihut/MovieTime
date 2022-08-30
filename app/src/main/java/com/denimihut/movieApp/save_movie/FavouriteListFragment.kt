package com.denimihut.movieApp.save_movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denimihut.movieApp.R
import com.denimihut.movieApp.adapter.FavoriteMoviesAdapter
import com.denimihut.movieApp.service_movie.Movies
import com.denimihut.movieApp.repository.MoviesRepository
import kotlinx.coroutines.*

class FavouriteListFragment : Fragment(R.layout.fragment_favourite_list) {
    private val movieRepository: MoviesRepository = MoviesRepository.instance
    private var movies: MutableList<Movies> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeListOfMovies(view)
    }

    private fun setupRecyclerView(view: View) {
        val linearLayoutManager = LinearLayoutManager(view.context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_favorites)

        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        linearLayoutManager.reverseLayout = false

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = FavoriteMoviesAdapter(movies)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun initializeListOfMovies(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            movies = movieRepository.getFavorite().toMutableList()
            withContext(Dispatchers.Main) {
                setupRecyclerView(view)
            }
        }
    }
}