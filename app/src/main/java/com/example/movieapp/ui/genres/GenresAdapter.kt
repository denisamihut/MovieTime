package com.example.movieapp.ui.genres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        val starIcon: ImageView = view.findViewById(R.id.heart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genresList[position]
        holder.genreName.text = genre.name

        if (position % 2 == 0) {
            holder.parentView.setBackgroundColor(
                ContextCompat.getColor(
                    holder.parentView.context,
                    R.color.grey_100
                )
            )
        }

        holder.parentView.setOnClickListener {
            genre.isSelected = !genre.isSelected
            holder.parentView.setBackgroundColor(
                when (genre.isSelected) {
                    true -> ContextCompat.getColor(holder.parentView.context, R.color.blue_dark)
                    else -> when (position % 2 == 0) {
                        true -> ContextCompat.getColor(holder.parentView.context, R.color.grey_100)
                        else -> ContextCompat.getColor(holder.parentView.context, R.color.white)
                    }
                }
            )

            holder.starIcon.visibility = when (genre.isSelected) {
                true -> View.VISIBLE
                else -> View.INVISIBLE
            }
        }
    }

    override fun getItemCount() = genresList.size
}