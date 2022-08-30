package com.denimihut.movieApp.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SaveMovieViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is save movie fragment"
    }
    val text: LiveData<String> = _text
}