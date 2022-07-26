package com.denimihut.movieApp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.denimihut.movieApp.R
import com.denimihut.movieApp.repository.ActorsRepository
import com.denimihut.movieApp.repository.GenresRepository
import kotlinx.coroutines.*
import java.lang.Runnable

private const val DELAY = 3000L

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val genresRepository = GenresRepository.instance
    private val actorsRepository = ActorsRepository.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initHandlerToOpenNextActivity()
    }

    private fun initHandlerToOpenNextActivity() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            openNextScreen()
        }
        handler?.postDelayed(runnable!!, DELAY)
    }

    private fun openNextScreen() {
        isSaved()
        finish()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun isSaved() {
        GlobalScope.launch(Dispatchers.IO) {
            val genreCount = genresRepository.getCount()
            val actorsCount = actorsRepository.getCount()
            withContext(Dispatchers.Main) {
                verifyIsSaved(genreCount, actorsCount)
            }
        }
    }

    private fun verifyIsSaved(genreCount: Int, actorsCount: Int) {
        val isSaved = genreCount > 0 && actorsCount > 0
        if (isSaved) {
            SearchActivity.open(this)
        } else
            OnBoardingActivity.open(this)
    }

    override fun onDestroy() {
        removeHandler()
        super.onDestroy()
    }

    override fun onBackPressed() {
        removeHandler()
        super.onBackPressed()
    }

    private fun removeHandler() {
        if (handler != null && runnable != null) {
            handler?.removeCallbacks(runnable!!)
            runnable = null
            handler = null
        }
    }
}