package com.denimihut.movieApp.ui.movies

import com.google.gson.annotations.SerializedName

class MoviesResponse(
    @SerializedName("id") var movieId: Int,
    @SerializedName("poster_path") var moviePoster: String?,
    @SerializedName("title") var movieName: String,
    @SerializedName("overview") var movieDescription: String?,
    @SerializedName("release_date") var movieRelease: String?,
)