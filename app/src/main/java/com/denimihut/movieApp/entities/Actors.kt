package com.denimihut.movieApp.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")

data class Actors(
    @PrimaryKey @NonNull @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "profile_path") val profile_path: String?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "is_selected") var isSelected: Boolean
) {
    override fun equals(other: Any?) = (other is Actors) && id == other.id

    override fun toString(): String {
        return "Actor(id=$id, name='$name', isSelected=$isSelected)"
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (profile_path?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + popularity.hashCode()
        result = 31 * result + isSelected.hashCode()
        return result
    }


}