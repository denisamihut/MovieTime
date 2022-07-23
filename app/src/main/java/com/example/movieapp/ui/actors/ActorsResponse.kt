package com.example.movieapp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("profile_path")
    var profile_path: String,
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("popularity")
    var popularity: Number,

)