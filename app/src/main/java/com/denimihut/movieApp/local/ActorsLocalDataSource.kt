package com.denimihut.movieApp.local

import com.denimihut.movieApp.dao.ActorsDAO
import com.denimihut.movieApp.database.Database
import com.denimihut.movieApp.entities.Actors

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