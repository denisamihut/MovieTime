package com.denimihut.movieApp.response

import com.denimihut.movieApp.response.GenresResponse
import com.google.gson.annotations.SerializedName

class GenresListResponse(
    @SerializedName("genres")
    var genres: List<GenresResponse>
)