package com.denimihut.movieApp.response

import com.denimihut.movieApp.response.ActorsResponse
import com.google.gson.annotations.SerializedName

class ActorsListResponse(
    @SerializedName("results")
    var actors: List<ActorsResponse>
)