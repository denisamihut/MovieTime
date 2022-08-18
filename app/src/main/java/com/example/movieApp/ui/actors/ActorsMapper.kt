package com.example.movieApp.ui.actors

class ActorsMapper {
    fun map(actorsResponse: ActorsResponse): Actors {
        return Actors(
            profile_path = actorsResponse.actor_photo,
            id = actorsResponse.actor_id,
            name = actorsResponse.actor_name,
            isSelected = false,
        )
    }
}