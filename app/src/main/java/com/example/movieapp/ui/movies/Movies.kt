package com.example.movieapp.ui.movies

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "total_results")

data class Movies(

    @ColumnInfo(name = "poster_path")
    var poster_path: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "release_date")
    var release_date: String,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean
) {
    override fun equals(other: Any?) = (other is Movies) && id == other.id

    override fun toString(): String {
        return "Movie(poster_path=$poster_path, overview='$overview', release_date=$release_date, id=$id, title='$title', isSelected=$isSelected)"
    }
}