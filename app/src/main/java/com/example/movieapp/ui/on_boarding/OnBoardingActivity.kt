package com.example.movieapp.ui.on_boarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.movieapp.R
import com.example.movieapp.SearchActivity
import com.example.movieapp.ui.actors.ActorsActivity
import com.example.movieapp.ui.actors.ActorsRepository
import com.example.movieapp.ui.genres.GenresActivity
import com.example.movieapp.ui.genres.GenresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnBoardingActivity : AppCompatActivity() {

    private val genresRepository = GenresRepository.instance
    private val actorsRepository = ActorsRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        setClickListeners()
    }

    private fun setClickListeners() {
        val genresButton = findViewById<Button>(R.id.btnGenres)
        val actorsButton = findViewById<Button>(R.id.btnActors)

        genresButton.setOnClickListener {
            startActivity(Intent(this, GenresActivity::class.java))
        }
        actorsButton.setOnClickListener {
            startActivity(Intent(this, ActorsActivity::class.java))
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, OnBoardingActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        verifyFilterIsSelected()
    }

    private fun verifyFilterIsSelected() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genresRepository.getCount()
            val actorCount = actorsRepository.getCount()
            withContext(Dispatchers.Main) {
                if (genreCount > 0 && actorCount > 0) {
                    SearchActivity.open(this@OnBoardingActivity)
                }
            }
        }
    }
}