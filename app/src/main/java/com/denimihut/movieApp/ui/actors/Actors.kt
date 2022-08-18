package com.denimihut.movieApp.ui.actors

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")

data class Actors(
    @ColumnInfo(name = "profile_path") val profile_path: String?,
    @PrimaryKey @NonNull @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "isSelected") var isSelected: Boolean
) {
    override fun equals(other: Any?) = (other is Actors) && id == other.id

    override fun toString(): String {
        return "Actor(id=$id, name='$name', isSelected=$isSelected)"
    }

    override fun hashCode(): Int {
        var result = profile_path?.hashCode() ?: 0
        result = 31 * result + id
        result = 31 * result + name.hashCode()
        result = 31 * result + isSelected.hashCode()
        return result
    }
}