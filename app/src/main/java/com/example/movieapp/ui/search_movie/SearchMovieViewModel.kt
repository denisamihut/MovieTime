package com.example.movieapp.ui.search_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchMovieViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is search movie Fragment"
    }
    val text: LiveData<String> = _text
}