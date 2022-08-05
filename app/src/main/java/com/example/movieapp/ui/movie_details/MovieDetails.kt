package com.example.movieapp.ui.movie_details

data class MovieDetails(
    var id: Int,
    var backdropPath: String?,
    var overview: String,
    var posterPath: String?,
    var releaseDate: String?,
    var title: String,
    var videos: VideosList?,
)