package com.example.movieApp.ui.genres

class GenresMapper {
    fun map(genresResponse: GenresResponse): Genres {
        return Genres(
            id = genresResponse.genre_id,
            name = genresResponse.genre_name,
            isSelected = false
        )
    }
}