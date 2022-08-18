package com.denimihut.movieApp.ui.genres

import com.google.gson.annotations.SerializedName

class GenresListResponse(
    @SerializedName("genres")
    var genres: List<GenresResponse>
)