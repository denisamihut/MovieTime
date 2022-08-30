package com.denimihut.movieApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denimihut.movieApp.R
import com.denimihut.movieApp.service_movie.Movies
import com.denimihut.movieApp.repository.MoviesRepository
import com.denimihut.movieApp.utils.Constants.IMAGE_URL
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteMoviesAdapter(
    private val moviesList: MutableList<Movies>
) : RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var favorite: Boolean = false
        var watched: Boolean = false

        val moviePoster = view.findViewById<ImageView>(R.id.ivMoviePoster)!!
        val movieName = view.findViewById<TextView>(R.id.tvMovieName)!!
        val movieDescription = view.findViewById<TextView>(R.id.tvMovieDescription)!!
        val movieRelease = view.findViewById<TextView>(R.id.tvMovieRelease)!!
        val btnDelete = view.findViewById<ImageButton>(R.id.btnDelete)!!
    }

    private val moviesRepository: MoviesRepository = MoviesRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_delete, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        Glide.with(holder.moviePoster.context).load(IMAGE_URL + movie.poster_path)
            .into(holder.moviePoster)

        holder.movieName.text = movie.title
        holder.movieDescription.text = movie.overview
        holder.movieRelease.text = movie.release_date

        holder.btnDelete.setOnClickListener {
            updateItem(moviesList[position])
            moviesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, moviesList.size)
        }

        holder.favorite = moviesList[position].isFavorite
        holder.watched = moviesList[position].isWatched
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun updateItem(movie: Movies) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList(moviesRepository.getAllLocalMovies())
            val idx = saved.indexOf(movie)
            if (idx != -1) saved[idx].isFavorite = !saved[idx].isFavorite
            if (!saved[idx].isFavorite && !saved[idx].isWatched) {
                moviesRepository.deleteLocal(saved[idx])
                saved.removeAt(idx)
            }
            moviesRepository.replaceAllLocal(saved.toList())
        }
    }

    override fun getItemCount() = moviesList.size
}
