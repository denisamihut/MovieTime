package com.denimihut.movieApp.service_genre

class GenresMapper {
    fun map(genresResponse: GenresResponse): Genres {
        return Genres(
            id = genresResponse.genreId,
            name = genresResponse.genreName,
            isSelected = false
        )
    }
}