package com.denimihut.movieApp.response

import com.google.gson.annotations.SerializedName

class ActorsResponse(
    @SerializedName("id") var actorId: Int,
    @SerializedName("profile_path") var actorPoster: String?,
    @SerializedName("name") var actorName: String,
    @SerializedName("popularity") var actorPopularity: Double
)