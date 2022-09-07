package com.denimihut.movieApp.service_genre

import com.google.gson.annotations.SerializedName

class GenresListResponse(
    @SerializedName("genres")
    var genres: List<GenresResponse>
)