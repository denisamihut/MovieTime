package com.example.movieapp.ui.save_movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.ui.movies.Movies
import com.example.movieapp.ui.movies.MoviesRepository
import com.example.movieapp.utils.Constants.IMAGE_URL
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WatchedMoviesAdapter(
    private val moviesList: MutableList<Movies>
) : RecyclerView.Adapter<WatchedMoviesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite: Boolean = false
        var watched: Boolean = false

        val itemIvMovie = view.findViewById<ImageView>(R.id.ivMoviePoster)!!
        val itemIvTitle = view.findViewById<TextView>(R.id.tvMovieTitle)!!
        val itemIvReleaseDate = view.findViewById<TextView>(R.id.ivMovieRelease)!!
        val itemIvOverview = view.findViewById<TextView>(R.id.tvMovieDescription)!!
        val itemBtnDelete = view.findViewById<ImageButton>(R.id.btnDelete)!!
    }

    private val moviesRep: MoviesRepository = MoviesRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_delete, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        Glide.with(holder.itemIvMovie.context).load(IMAGE_URL + movie.poster_path)
            .into(holder.itemIvMovie)
        holder.itemIvTitle.text = movie.title
        holder.itemIvOverview.text = movie.overview
        holder.itemIvReleaseDate.text = movie.release_date

        holder.favorite = moviesList[position].isFavorite
        holder.watched = moviesList[position].isWatched

        holder.itemBtnDelete.setOnClickListener {
            updateItem(moviesList[position])
            moviesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, moviesList.size)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun updateItem(movie: Movies) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList(moviesRep.getAllLocalMovies())
            val idx = saved.indexOf(movie)
            if (idx != -1) saved[idx].isFavorite = !saved[idx].isFavorite
            if (!saved[idx].isFavorite && !saved[idx].isWatched) {
                moviesRep.deleteLocal(saved[idx])
                saved.removeAt(idx)
            }
            moviesRep.replaceAllLocal(saved.toList())
        }
    }

    override fun getItemCount() = moviesList.size
}