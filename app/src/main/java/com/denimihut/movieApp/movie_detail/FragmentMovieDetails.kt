package com.denimihut.movieApp.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denimihut.movieApp.databinding.FragmentMovieDetailsBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.*

class FragmentMovieDetails : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(requireActivity())[MovieDetailsViewModel::class.java]

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.movie = viewModel.getMovieDetails()
            withContext(Dispatchers.Main) {
                binding.tvReleaseDate.text = viewModel.movie?.releaseDate
                binding.tvDescription.text = viewModel.movie?.overview
                binding.tvTitle.text = viewModel.movie?.title
                loadYtbVideos()
            }
        }
    }

    private fun loadYtbVideos() {
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                viewModel.movie?.videos?.results?.get(0)
                    ?.let { youTubePlayer.loadVideo(findYoutubeTrailer(), 0f) }
            }
        })
    }

    private fun findYoutubeTrailer(): String {
        viewModel.movie?.videos?.results?.let { videoList ->
            for (video in videoList) {
                if (video.type == "Trailer")
                    return video.key
            }
        }
        return ""
    }
}