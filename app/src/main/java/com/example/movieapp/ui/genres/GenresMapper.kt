package com.example.movieapp.ui.genres

class GenresMapper {
    fun map(genresResponse: GenresResponse): Genres {
        return Genres(
            id = genresResponse.id,
            name = genresResponse.name,
            isSelected = false
        )
    }
}