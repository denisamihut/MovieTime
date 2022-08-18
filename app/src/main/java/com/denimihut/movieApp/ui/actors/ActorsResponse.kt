package com.denimihut.movieApp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsResponse(
    @SerializedName("profile_path") var actor_photo: String?,
    @SerializedName("id") var actor_id: Int,
    @SerializedName("name") var actor_name: String
)