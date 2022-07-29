package com.example.movieapp.ui.movies


class MoviesMapper {
    fun map(moviesResponse: MoviesResponse): Movies {
        return Movies(
            poster_path = moviesResponse.poster_path,
            overview = moviesResponse.overview,
            release_date = moviesResponse.release_date,
            id = moviesResponse.id,
            title = moviesResponse.title,
            isSelected = false
        )
    }
}