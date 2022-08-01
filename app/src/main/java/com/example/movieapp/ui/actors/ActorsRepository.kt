package com.example.movieapp.ui.actors

import com.example.movieapp.database.Database
import com.example.movieapp.network.APIClient

class ActorsRepository private constructor() {
    companion object {
        val instance = ActorsRepository()
    }

    private val actorsRemoteDataSource = ActorsRemoteDataSource(APIClient.instance.retrofit)
    private val actorsLocalDataSource = ActorsLocalDataSource(Database.instance)

    fun getAllRemoteActors() = actorsRemoteDataSource.getActors()

    fun getAllLocalIds() = actorsLocalDataSource.getAllIds()
    fun getAllLocalActors() = actorsLocalDataSource.getAll()
    fun saveLocal(actor: Actors) = actorsLocalDataSource.save(actor)
    fun saveAllLocal(actors: List<Actors>) = actorsLocalDataSource.saveAll(actors)
    fun deleteLocal(actor: Actors) = actorsLocalDataSource.delete(actor)
    fun deleteAllLocal() = actorsLocalDataSource.deleteAll()
    fun deleteAllLocal(actors: List<Actors>) = actorsLocalDataSource.deleteAll(actors)
    fun replaceAllLocal(actors: List<Actors>) = actorsLocalDataSource.replaceAll(actors)
    fun getCount() = actorsLocalDataSource.getCount()
}