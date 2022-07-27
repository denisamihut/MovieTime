package com.example.movieapp.ui.movies

import com.google.gson.annotations.SerializedName

class MoviesListResponse (
    @SerializedName("total_results")
    var movies: List<MoviesResponse>
)