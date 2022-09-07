package com.denimihut.movieApp.movie_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denimihut.movieApp.network.APIClient
import com.denimihut.movieApp.remote.MoviesRemoteDataSource

class MovieDetailsViewModel : ViewModel() {
    val currentMovieId = MutableLiveData<Int>()

    var movie: MovieDetails? = null
    private val movieRemoteDataSource = MoviesRemoteDataSource(APIClient.instance.retrofit)

    fun getMovieDetails(): MovieDetails? {
        return currentMovieId.value?.let { movieRemoteDataSource.getMovieDetails(it) }
    }
}