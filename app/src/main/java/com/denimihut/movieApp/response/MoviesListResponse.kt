package com.denimihut.movieApp.response

import com.google.gson.annotations.SerializedName

class MoviesListResponse(
    @SerializedName("results")
    var movies: List<MoviesResponse>
)