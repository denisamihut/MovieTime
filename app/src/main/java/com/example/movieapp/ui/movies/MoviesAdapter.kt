package com.example.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.utils.Constants.IMAGE_URL

class MoviesAdapter(private val moviesList: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.txtMovieTitle)
        val parentView: ConstraintLayout = view.findViewById(R.id.clMovie)
        val txtDescription: TextView = view.findViewById(R.id.txtMovieDescription)
        val movieRelease: TextView = view.findViewById(R.id.txtMovieRelease)

        val imageView: ImageView = view.findViewById(R.id.imgMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.movieTitle.text = movie.title
        holder.txtDescription.text = movie.overview
        holder.movieRelease.text = movie.release_date

        Glide.with(holder.imageView.context).load(IMAGE_URL + movie.poster_path)
            .into(holder.imageView)
    }

    override fun getItemCount() = moviesList.size
}