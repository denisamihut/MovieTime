package com.denimihut.movieApp.response

import com.google.gson.annotations.SerializedName

class GenresResponse(
    @SerializedName("id") var genreId: Int,
    @SerializedName("name") var genreName: String
)