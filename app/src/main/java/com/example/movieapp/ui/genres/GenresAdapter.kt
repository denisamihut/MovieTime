package com.example.movieapp.ui.genres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class GenresAdapter(private val genresList: List<Genres>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreName: TextView = view.findViewById(R.id.tv_name)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genresList[position]
        holder.genreName.text = genre.name

        selectGenre(holder, genre)

        holder.parentView.setOnClickListener {
            genre.isSelected = !genre.isSelected
            selectGenre(holder, genre)

        }
    }

    private fun selectGenre(holder: ViewHolder, genre: Genres) {
        holder.parentView.setBackgroundColor(
            when (genre.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.blue)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.white)
            }
        )

        holder.genreName.setTextColor(
            when (genre.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.white)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.black)
            }
        )
    }

    override fun getItemCount() = genresList.size
}