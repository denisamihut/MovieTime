package com.denimihut.movieApp.ui.movies

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movies(
    @PrimaryKey @NonNull @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String?,
    @ColumnInfo(name = "release_date") val release_date: String?,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean,
    @ColumnInfo(name = "is_watched") var isWatched: Boolean

) {

    override fun equals(other: Any?) = (other is Movies) && id == other.id
    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (poster_path?.hashCode() ?: 0)
        result = 31 * result + title.hashCode()
        result = 31 * result + (overview?.hashCode() ?: 0)
        result = 31 * result + (release_date?.hashCode() ?: 0)
        result = 31 * result + isFavorite.hashCode()
        result = 31 * result + isWatched.hashCode()
        return result
    }
}