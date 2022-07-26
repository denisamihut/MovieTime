package com.denimihut.movieApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.denimihut.movieApp.R
import com.denimihut.movieApp.entities.Genres

class GenresAdapter(private val genresList: List<Genres>) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parentView: ConstraintLayout = view.findViewById(R.id.clGenre)
        val genreName: TextView = view.findViewById(R.id.tvGenreName)
        val shape1: ImageView = view.findViewById(R.id.ivActorBackground)
        val shape2: ImageView = view.findViewById(R.id.ivActorBackground2)
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

        holder.genreName.setTextColor(
            when (genre.isSelected) {
                true -> ContextCompat.getColor(holder.parentView.context, R.color.grey_neon)
                else -> ContextCompat.getColor(holder.parentView.context, R.color.grey_neon)
            }
        )

        holder.shape1.visibility  = when (genre.isSelected) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        holder.shape2.visibility  = when (genre.isSelected) {
            true -> View.INVISIBLE
            else -> View.VISIBLE
        }
    }

    override fun getItemCount() = genresList.size
}