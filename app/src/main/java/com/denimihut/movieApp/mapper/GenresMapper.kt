package com.denimihut.movieApp.mapper

import com.denimihut.movieApp.entities.Genres
import com.denimihut.movieApp.response.GenresResponse

class GenresMapper {
    fun map(genresResponse: GenresResponse): Genres {
        return Genres(
            id = genresResponse.genreId,
            name = genresResponse.genreName,
            isSelected = false
        )
    }
}