package com.example.movieapp.ui.actors

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")

data class Actors(
    @ColumnInfo(name = "profile_path")
    var profile_path: String?,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean
) {
    override fun equals(other: Any?) = (other is Actors) && id == other.id

    override fun toString(): String {
        return "Actor(id=$id, name='$name', isSelected=$isSelected)"
    }
}