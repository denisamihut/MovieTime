package com.denimihut.movieApp.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denimihut.movieApp.R
import com.denimihut.movieApp.repository.ActorsRepository
import com.denimihut.movieApp.repository.GenresRepository
import com.denimihut.movieApp.movie_detail.MovieDetailsViewModel
import com.denimihut.movieApp.entities.Movies
import com.denimihut.movieApp.adapter.MoviesAdapter
import com.denimihut.movieApp.databinding.FragmentAllMoviesBinding
import com.denimihut.movieApp.repository.MoviesRepository
import kotlinx.coroutines.*

class AllMoviesFragment : Fragment(R.layout.fragment_all_movies) {
    private var movies: List<Movies> = emptyList()

    private var _binding: FragmentAllMoviesBinding? = null
    private val binding get() = _binding!!
    private val moviesRepository = MoviesRepository.instance
    private val genresRepository = GenresRepository.instance
    private val actorsRepository = ActorsRepository.instance

    private var genresIds = ""
    private var actorsIds = ""

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[AllMoviesViewModel::class.java]

        _binding = FragmentAllMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        viewModel = ViewModelProvider(requireActivity())[MovieDetailsViewModel::class.java]
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun preselectSaveGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenresIds: List<Int> = genresRepository.getAllLocalIds()
            val savedActorsIds: List<Int> = actorsRepository.getAllLocalIds()

            genresIds = savedGenresIds.joinToString(separator = "|") { "$it" }
            actorsIds = savedActorsIds.joinToString(separator = "|") { "$it" }

            withContext(Dispatchers.Main) {
                getMovies()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            movies = moviesRepository.getAllRemoteMovies(actorsIds, genresIds)
            withContext(Dispatchers.Main) {
                moviesLoaded()
            }
        }
        preselectItems()
    }

    private fun moviesLoaded() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rvMovies = binding.rvMovies
        rvMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovies.adapter = MoviesAdapter(movies, { navigateToMovieDetails() }, viewModel)
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

    @OptIn(DelicateCoroutinesApi::class)
    private fun getSearchedMovies(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            movies = moviesRepository.getAllSearchedMovies(query)
            withContext(Dispatchers.Main) {
                binding.rvMovies.adapter =
                    MoviesAdapter(movies, { navigateToMovieDetails() }, viewModel)
            }
        }
        preselectItems()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun preselectItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = moviesRepository.getAllLocalMovies()
            withContext(Dispatchers.Main) {
                movies.forEach {
                    val idx = saved.indexOf(it)
                    it.isFavorite = (idx != -1) && saved[idx].isFavorite
                    it.isWatched = (idx != -1) && saved[idx].isWatched
                }
            }
        }
    }

    private fun navigateToMovieDetails() {
        findNavController().navigate(R.id.fragmentMovieDetails)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}