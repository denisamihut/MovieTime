package com.denimihut.movieApp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.denimihut.movieApp.R
import com.denimihut.movieApp.repository.ActorsRepository
import com.denimihut.movieApp.repository.GenresRepository
import kotlinx.coroutines.*

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

    @OptIn(DelicateCoroutinesApi::class)
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