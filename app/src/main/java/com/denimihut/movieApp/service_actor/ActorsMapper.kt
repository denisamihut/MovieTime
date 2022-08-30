package com.denimihut.movieApp.ui.actors

class ActorsMapper {
    fun map(actorsResponse: ActorsResponse): Actors {
        return Actors(
            id = actorsResponse.actorId,
            profile_path = actorsResponse.actorPoster,
            name = actorsResponse.actorName,
            isSelected = false,
        )
    }
}