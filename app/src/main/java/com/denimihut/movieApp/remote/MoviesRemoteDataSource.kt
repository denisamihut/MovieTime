package com.denimihut.movieApp.remote

import com.denimihut.movieApp.APIs.MoviesAPIService
import com.denimihut.movieApp.entities.Movies
import com.denimihut.movieApp.mapper.MoviesMapper
import com.denimihut.movieApp.network.executeAndDeliver
import com.denimihut.movieApp.movie_detail.MovieDetails
import com.denimihut.movieApp.mapper.MovieDetailsMapper
import com.denimihut.movieApp.utils.Constants.API_KEY
import com.denimihut.movieApp.utils.Constants.APPEND_TO_RESPONSE
import com.denimihut.movieApp.utils.Constants.LANGUAGE
import retrofit2.Retrofit

class MoviesRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MoviesAPIService = retrofit.create(MoviesAPIService::class.java)
    private val movieMapper = MoviesMapper()
    private val movieDetailsMapper = MovieDetailsMapper()

    fun getMovies(withCast: String, withGenres: String): List<Movies> {
        return apiService.getMovies(API_KEY, LANGUAGE, withCast, withGenres)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getSearchedMovies(query: String): List<Movies> {
        return apiService.getSearchedMovies(API_KEY, LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { movieMapper.map(it) }
    }

    fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId, API_KEY, LANGUAGE, APPEND_TO_RESPONSE)
            .executeAndDeliver()
            .let { movieDetailsMapper.map(it) }
    }
}