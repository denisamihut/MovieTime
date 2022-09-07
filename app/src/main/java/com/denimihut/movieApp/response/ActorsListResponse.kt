package com.denimihut.movieApp.service_actor

import com.google.gson.annotations.SerializedName

class ActorsListResponse(
    @SerializedName("results")
    var actors: List<ActorsResponse>
)