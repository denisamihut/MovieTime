package com.denimihut.movieApp.app

import android.app.Application
import com.denimihut.movieApp.database.Database

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Database.instance.initialize(this)
    }
}