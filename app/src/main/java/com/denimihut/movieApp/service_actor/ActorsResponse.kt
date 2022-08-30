package com.denimihut.movieApp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsResponse(
    @SerializedName("id") var actorId: Int,
    @SerializedName("profile_path") var actorPoster: String?,
    @SerializedName("name") var actorName: String
)