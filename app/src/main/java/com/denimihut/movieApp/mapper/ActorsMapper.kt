package com.denimihut.movieApp.service_actor

class ActorsMapper {
    fun map(actorsResponse: ActorsResponse): Actors {
        return Actors(
            id = actorsResponse.actorId,
            profile_path = actorsResponse.actorPoster,
            name = actorsResponse.actorName,
            popularity = actorsResponse.actorPopularity,
            isSelected = false
        )
    }
}