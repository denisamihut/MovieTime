package com.denimihut.movieApp.mapper

import com.denimihut.movieApp.entities.Actors
import com.denimihut.movieApp.response.ActorsResponse

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