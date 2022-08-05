package com.example.movieapp.ui.actors

import com.example.movieapp.database.Database

class ActorsLocalDataSource(database: Database) {

    private val actorsDAO: ActorsDAO = database.movieAppDatabase.actorsDao()

    fun getAll() = actorsDAO.getAll()
    fun getAllIds() = actorsDAO.getAllIds()
    fun save(actor: Actors) = actorsDAO.save(actor)
    fun saveAll(actors: List<Actors>) = actorsDAO.saveAll(actors)
    fun delete(actor: Actors) = actorsDAO.delete(actor)
    fun deleteAll() = actorsDAO.deleteAll()
    fun deleteAll(actors: List<Actors>) = actorsDAO.deleteAll(actors)
    fun replaceAll(actors: List<Actors>) = actorsDAO.replaceAll(actors)
    fun getCount() = actorsDAO.getCount()
}