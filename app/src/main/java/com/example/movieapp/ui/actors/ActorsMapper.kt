package com.example.movieapp.ui.actors

class ActorsMapper {
    fun map(actorsResponse: ActorsResponse): Actors {
        return Actors(
            profile_path = actorsResponse.profile_path,
            adult = actorsResponse.adult,
            id = actorsResponse.id,
            name = actorsResponse.name,
            popularity = actorsResponse.popularity,
            isSelected = false,
        )
    }
}