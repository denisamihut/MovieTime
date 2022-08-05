package com.example.movieapp.ui.movies

import com.example.movieapp.network.executeAndDeliver
import com.example.movieapp.ui.movie_details.MovieDetails
import com.example.movieapp.ui.movie_details.MovieDetailsMapper
import com.example.movieapp.utils.Constants.API_KEY
import com.example.movieapp.utils.Constants.APPEND_TO_RESPONSE
import com.example.movieapp.utils.Constants.LANGUAGE
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