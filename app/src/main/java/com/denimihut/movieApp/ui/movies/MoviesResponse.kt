package com.denimihut.movieApp.ui.movies

import com.google.gson.annotations.SerializedName

class MoviesResponse(
    @SerializedName("id") var movie_id: Int,
    @SerializedName("title") var movie_name: String,
    @SerializedName("poster_path") var movie_poster: String?,
    @SerializedName("overview") var movie_description: String?,
    @SerializedName("release_date") var movie_release: String?,
)