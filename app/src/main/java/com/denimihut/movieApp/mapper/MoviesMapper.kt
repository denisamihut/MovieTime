package com.denimihut.movieApp.mapper

import com.denimihut.movieApp.entities.Movies
import com.denimihut.movieApp.response.MoviesResponse

class MoviesMapper {
    fun map(moviesResponse: MoviesResponse): Movies {
        return Movies(
            id = moviesResponse.movieId,
            poster_path = moviesResponse.moviePoster,
            title = moviesResponse.movieName,
            overview = moviesResponse.movieDescription,
            release_date = moviesResponse.movieRelease,
            isFavorite = false,
            isWatched = false
        )
    }
}