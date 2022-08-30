package com.denimihut.movieApp.service_movie

import com.google.gson.annotations.SerializedName

class MoviesListResponse(
    @SerializedName("results")
    var movies: List<MoviesResponse>
)