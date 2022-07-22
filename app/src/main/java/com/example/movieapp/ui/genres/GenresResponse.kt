package com.example.movieapp.ui.genres

import com.google.gson.annotations.SerializedName

class GenresResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)