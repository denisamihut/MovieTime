package com.example.movieApp.app

import android.app.Application
import com.example.movieApp.database.Database

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Database.instance.initialize(this)
    }
}