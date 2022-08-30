package com.denimihut.movieApp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsListResponse(
    @SerializedName("results")
    var actors: List<ActorsResponse>
)