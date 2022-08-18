package com.denimihut.movieApp.network

import com.denimihut.movieApp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient private constructor() {
    companion object {
        val instance = APIClient()
    }

    private val loggingInterceptor: LoggingInterceptor by lazy {
        LoggingInterceptor().apply {
            this.setLevel(LoggingInterceptor.Level.BODY)
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}