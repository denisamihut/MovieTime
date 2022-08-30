package com.denimihut.movieApp.ui.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denimihut.movieApp.network.APIClient
import com.denimihut.movieApp.ui.movies.MoviesRemoteDataSource

class MovieDetailsViewModel : ViewModel() {
    val currentMovieId = MutableLiveData<Int>()

    var movie: MovieDetails? = null
    private val movieRemoteDataSource = MoviesRemoteDataSource(APIClient.instance.retrofit)

    fun getMovieDetails(): MovieDetails? {
        return currentMovieId.value?.let { movieRemoteDataSource.getMovieDetails(it) }
    }
}