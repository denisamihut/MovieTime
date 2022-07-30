package com.example.movieapp.ui.search_movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchMoviesBinding
import com.example.movieapp.ui.actors.ActorsRepository
import com.example.movieapp.ui.genres.Genres
import com.example.movieapp.ui.genres.GenresRepository
import com.example.movieapp.ui.movies.Movies
import com.example.movieapp.ui.movies.MoviesAdapter
import com.example.movieapp.ui.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMovieFragment : Fragment() {

    private var _binding: FragmentSearchMoviesBinding? = null
    private var movies: List<Movies> = emptyList()
    private val moviesRepository = MoviesRepository.instance
    private val genresRepository = GenresRepository.instance
    private val actorsRepository = ActorsRepository.instance

    private var genreIds = ""
    private var actorIds = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(SearchMovieViewModel::class.java)

        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.moviesInfo
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getQueryParams()
    }

    private fun getQueryParams() {
        preselectSavedGenres()
//        preselectSavedActors()
    }

    private fun preselectSavedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenresIds: List<Int> = genresRepository.getAllLocalIds()
            genreIds = savedGenresIds.joinToString(separator = "|") { "$it" }
            val savedActorsIds: List<Int> = actorsRepository.getAllLocalIds()
            actorIds = savedActorsIds.joinToString(separator = "|") { "$it" }
//            Log.e("Testing", "rezultat: $preparedString")
            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

//    private fun preselectSavedActors() {
//        GlobalScope.launch(Dispatchers.IO) {
//            val savedActorsIds: List<Int> = actorsRepository.getAllLocalIds()
//            actorIds = savedActorsIds.joinToString { it -> "|$it" }
//            withContext(Dispatchers.Main) {
//                getMovies()
//            }
//        }
//    }

    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            movies = moviesRepository.getAllRemoteMovies()
            withContext(Dispatchers.Main) {
                moviesLoaded(movies)
            }
        }
    }

    private fun moviesLoaded(movies: List<Movies>) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rvMovies = view?.findViewById<RecyclerView>(R.id.rv_movies)
        rvMovies?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies?.adapter = MoviesAdapter(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}