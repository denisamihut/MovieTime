package com.example.movieapp.ui.movie_details

class MovieDetailsMapper {
    fun map(movieResponse: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
            id = movieResponse.id,
            title = movieResponse.title,
            backdropPath = movieResponse.backdropPath,
            overview = movieResponse.overview,
            posterPath = movieResponse.posterPath,
            releaseDate = movieResponse.releaseDate,
            videos = movieResponse.videos
        )
    }
}