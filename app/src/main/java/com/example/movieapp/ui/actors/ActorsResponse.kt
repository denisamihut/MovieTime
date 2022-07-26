package com.example.movieapp.ui.actors

import com.google.gson.annotations.SerializedName

class ActorsResponse(

    @SerializedName("profile_path")
    var profile_path: String?,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,

)