package com.denimihut.movieApp.service_movie_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denimihut.movieApp.network.APIClient
import com.denimihut.movieApp.service_movie.MoviesRemoteDataSource

class MovieDetailsViewModel : ViewModel() {
    val currentMovieId = MutableLiveData<Int>()

    var movie: MovieDetails? = null
    private val movieRemoteDataSource = MoviesRemoteDataSource(APIClient.instance.retrofit)

    fun getMovieDetails(): MovieDetails? {
        return currentMovieId.value?.let { movieRemoteDataSource.getMovieDetails(it) }
    }
}