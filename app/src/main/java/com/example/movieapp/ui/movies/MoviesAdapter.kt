package com.example.movieapp.ui.movies

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.utils.Constants.IMAGE_URL
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.Dispatcher

class MoviesAdapter(
    private val moviesList: List<Movies>
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var favorite: Boolean = false
        var watched: Boolean = false

        val movieTitle: TextView = view.findViewById(R.id.tvMovieTitle)
        val parentView: ConstraintLayout = view.findViewById(R.id.clMovie)
        val imageView: ImageView = view.findViewById(R.id.ivMoviePoster)
        val txtDescription: TextView = view.findViewById(R.id.tvMovieDescription)
        val movieRelease: TextView = view.findViewById(R.id.ivMovieRelease)

        val btnFavorite: Button = view.findViewById(R.id.btnFavorites)
        val btnWatched: Button = view.findViewById(R.id.btnWatched)
    }

    private val moviesRepository: MoviesRepository = MoviesRepository.instance

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

        holder.favorite = movie.isFavorite
        holder.watched = movie.isWatched

        updateFavoriteButton(holder)
        updateWatchedButton(holder)

        holder.btnFavorite.setOnClickListener {
            holder.favorite = !holder.favorite
            movie.isFavorite = holder.favorite
            updateFavoriteButton(holder)
            updateDatabase(moviesList[position])
        }

        holder.btnWatched.setOnClickListener {
            holder.favorite = !holder.favorite
            movie.isWatched = holder.favorite
            updateWatchedButton(holder)
            updateDatabase(moviesList[position])
        }

        Glide.with(holder.imageView.context).load(IMAGE_URL + movie.poster_path)
            .into(holder.imageView)
    }

    private fun updateFavoriteButton(holder: ViewHolder) {
        holder.btnFavorite.setBackgroundColor(
            when (holder.favorite) {
                true -> R.color.blue
                else -> R.color.black
            }
        )
    }

    private fun updateWatchedButton(holder: ViewHolder) {
        holder.btnWatched.background.colorFilter = PorterDuffColorFilter(
            when (holder.watched) {
                true -> R.color.blue
                else -> R.color.black
            }, PorterDuff.Mode.SRC_IN
        )
    }

    private fun filterWithFlags() = moviesList.filter { it.isFavorite || it.isWatched }

    private fun updateDatabase(item: Movies) {
        GlobalScope.launch(Dispatchers.IO) {
            val saved = ArrayList<Movies>(moviesRepository.getAllLocalMovies())
            val filtered = ArrayList<Movies>(filterWithFlags())

            saved.remove(item)

            moviesRepository.replaceAllLocal(saved.union(filtered).toList())
        }
    }

    override fun getItemCount() = moviesList.size
}