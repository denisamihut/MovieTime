package com.example.movieapp.app

import android.app.Application
import com.example.movieapp.database.Database

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Database.instance.initialize(this)
    }
}