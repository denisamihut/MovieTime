package com.example.movieapp.ui.actors

import com.example.movieapp.network.APIClient

class ActorsRepository private constructor(){
    companion object {
        val instance = ActorsRepository()
    }

    private val ActorsRemoteDataSource = ActorsRemoteDataSource(APIClient.instance.retrofit)

    fun getAllRemoteActors() = ActorsRemoteDataSource.getActors()
}