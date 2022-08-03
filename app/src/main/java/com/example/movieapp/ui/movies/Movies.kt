package com.example.movieapp.ui.movies

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")

data class Movies(

    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "release_date") val release_date: String?,
    @PrimaryKey @NonNull @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean,
    @ColumnInfo(name = "is_watched") var isWatched: Boolean

) {

    override fun equals(other: Any?) = (other is Movies) && id == other.id

}