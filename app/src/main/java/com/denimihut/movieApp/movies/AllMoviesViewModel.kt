package com.denimihut.movieApp.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllMoviesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply { }
    val text: LiveData<String> = _text
}