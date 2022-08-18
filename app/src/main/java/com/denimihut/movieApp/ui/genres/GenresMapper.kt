package com.denimihut.movieApp.ui.genres

class GenresMapper {
    fun map(genresResponse: GenresResponse): Genres {
        return Genres(
            id = genresResponse.genreId,
            name = genresResponse.genreName,
            isSelected = false
        )
    }
}