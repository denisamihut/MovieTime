package com.denimihut.movieApp.service_movie

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