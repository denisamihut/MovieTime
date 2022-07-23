package com.example.movieapp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsListResponse(
    @SerializedName("results")
    var results: List<ActorsResponse>
)