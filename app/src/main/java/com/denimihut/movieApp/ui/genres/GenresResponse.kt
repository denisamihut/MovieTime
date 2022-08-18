package com.denimihut.movieApp.ui.genres

import com.google.gson.annotations.SerializedName

class GenresResponse(
    @SerializedName("id") var genre_id: Int,
    @SerializedName("name") var genre_name: String
)