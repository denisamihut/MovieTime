package com.denimihut.movieApp.movie_detail

data class MovieDetails(
    var id: Int,
    var backdropPath: String?,
    var overview: String,
    var posterPath: String?,
    var releaseDate: String?,
    var title: String,
    var videos: VideosList?,
)