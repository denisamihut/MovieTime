package com.example.movieapp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)