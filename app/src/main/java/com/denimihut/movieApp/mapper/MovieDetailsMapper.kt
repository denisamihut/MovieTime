package com.denimihut.movieApp.service_movie_detail

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