package com.example.movieapp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsListResponse(
    @SerializedName("actors")
    var actors: List<ActorsResponse>
)