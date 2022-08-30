package com.denimihut.movieApp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denimihut.movieApp.R
import com.denimihut.movieApp.service_movie.Movies
import com.denimihut.movieApp.repository.MoviesRepository
import com.denimihut.movieApp.service_movie_detail.MovieDetailsViewModel
import com.denimihut.movieApp.utils.Constants.IMAGE_URL
import kotlinx.coroutines.*

class MoviesAdapter(
    private val moviesList: List<Movies>,
    private val detailsCallback: (() -> Unit)?,
    private val viewModel: MovieDetailsViewModel
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var favorite: Boolean = false
        var watched: Boolean = false

        val parentView: ConstraintLayout = view.findViewById(R.id.clMovie)
        val imageView: ImageView = view.findViewById(R.id.ivMoviePoster)
        val movieName: TextView = view.findViewById(R.id.tvMovieName)
        val movieDescription: TextView = view.findViewById(R.id.tvMovieDescription)
        val movieRelease: TextView = view.findViewById(R.id.tvMovieRelease)

        val btnFavorite: ImageButton = view.findViewById(R.id.btnFavorites)
        val btnWatched: ImageButton = view.findViewById(R.id.btnWatched)
    }

    private val moviesRepository: MoviesRepository = MoviesRepository.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]

        Glide.with(holder.imageView.context).load(IMAGE_URL + movie.poster_path)
            .into(holder.imageView)

        holder.movieName.text = movie.title
        holder.movieDescription.text = movie.overview
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
            holder.watched = !holder.watched
            movie.isWatched = holder.watched
            updateWatchedButton(holder)
            updateDatabase(moviesList[position])
        }

        holder.parentView.setOnClickListener {
            viewModel.currentMovieId.postValue(movie.id)
            detailsCallback?.invoke()
        }
    }

    private fun updateFavoriteButton(holder: ViewHolder) {
        holder.btnFavorite.setImageResource(
            when (holder.favorite) {
                true -> R.drawable.ic_baseline_favorite_24
                else -> R.drawable.ic_baseline_favorite_border_24
            }
        )
    }

    private fun updateWatchedButton(holder: ViewHolder) {
        holder.btnWatched.setImageResource(
            when (holder.watched) {
                true -> R.drawable.ic_baseline_bookmark_24
                else -> R.drawable.ic_baseline_bookmark_border_24
            }
        )
    }

    private fun filterWithFlags() = moviesList.filter { it.isFavorite || it.isWatched }

    @OptIn(DelicateCoroutinesApi::class)
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