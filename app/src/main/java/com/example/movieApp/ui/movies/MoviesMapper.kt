package com.example.movieApp.ui.movies

class MoviesMapper {
    fun map(moviesResponse: MoviesResponse): Movies {
        return Movies(
            poster_path = moviesResponse.movie_poster,
            overview = moviesResponse.movie_description,
            release_date = moviesResponse.movie_release,
            id = moviesResponse.movie_id,
            title = moviesResponse.movie_name,
            isFavorite = false,
            isWatched = false
        )
    }
}