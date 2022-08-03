package com.example.movieapp.ui.search_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSearchMoviesBinding
import com.example.movieapp.ui.actors.ActorsRepository
import com.example.movieapp.ui.genres.GenresRepository
import com.example.movieapp.ui.movies.Movies
import com.example.movieapp.ui.movies.MoviesAdapter
import com.example.movieapp.ui.movies.MoviesRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMovieFragment : Fragment(R.layout.fragment_search_movies) {

    private var _binding: FragmentSearchMoviesBinding? = null
    private var movies: List<Movies> = emptyList()
    private val moviesRepository = MoviesRepository.instance
    private val genresRepository = GenresRepository.instance
    private val actorsRepository = ActorsRepository.instance

    private var genresIds = ""
    private var actorsIds = ""
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchMoviesViewModel =
            ViewModelProvider(this).get(SearchMovieViewModel::class.java)

        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getQueryParams()
        setSearchTextListener()
    }

    private fun getQueryParams() {
        preselectSaveGenres()
    }

    private fun preselectSaveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenresIds: List<Int> = genresRepository.getAllLocalIds()
            genresIds = savedGenresIds.joinToString(separator = "|") { "$it" }

            val savedActorsIds: List<Int> = actorsRepository.getAllLocalIds()
            actorsIds = savedActorsIds.joinToString(separator = "|") { "$it" }

            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            movies = moviesRepository.getAllRemoteMovies(actorsIds, genresIds)
            withContext(Dispatchers.Main) {
                setupRecyclerView()
            }
        }
    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovies
        rvMovies?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies?.adapter = MoviesAdapter(movies)

    }

    private fun setSearchTextListener() {
        val search = binding.svMovies
        search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if ((newText?.length ?: 0) >= 1) {
                    getSearchedMovies(newText ?: "")
                } else
                    getMovies()
                return false
            }
        })
    }

    private fun getSearchedMovies(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            movies = moviesRepository.getAllSearchedMovies(query)
            withContext(Dispatchers.Main) {
                binding.rvMovies.adapter = MoviesAdapter(movies)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}