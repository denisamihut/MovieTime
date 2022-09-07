package com.denimihut.movieApp.repository

import com.denimihut.movieApp.database.Database
import com.denimihut.movieApp.network.APIClient
import com.denimihut.movieApp.entities.Actors
import com.denimihut.movieApp.service_actor.ActorsLocalDataSource
import com.denimihut.movieApp.service_actor.ActorsRemoteDataSource

class ActorsRepository private constructor() {
    companion object {
        val instance = ActorsRepository()
    }

    private val actorsRemoteDataSource = ActorsRemoteDataSource(APIClient.instance.retrofit)
    private val actorsLocalDataSource = ActorsLocalDataSource(Database.instance)

    fun getAllSearchedActors(query: String) = actorsRemoteDataSource.getSearchedActors(query)


    fun getAllRemoteActors() = actorsRemoteDataSource.getActors()

    fun getAllLocalIds() = actorsLocalDataSource.getAllIds()
    fun getAllLocalActors() = actorsLocalDataSource.getAll()
    fun saveAllLocal(actors: List<Actors>) = actorsLocalDataSource.saveAll(actors)
    fun deleteAllLocal() = actorsLocalDataSource.deleteAll()
    fun getCount() = actorsLocalDataSource.getCount()
    //    fun saveLocal(actor: Actors) = actorsLocalDataSource.save(actor)
    //    fun deleteLocal(actor: Actors) = actorsLocalDataSource.delete(actor)
    //    fun deleteAllLocal(actors: List<Actors>) = actorsLocalDataSource.deleteAll(actors)
    //    fun replaceAllLocal(actors: List<Actors>) = actorsLocalDataSource.replaceAll(actors)
}