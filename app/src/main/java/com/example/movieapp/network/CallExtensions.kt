package com.example.movieapp.network

import retrofit2.Call
import retrofit2.Response

fun Call<Void>.executeWithEmptyResponse(): Any {
    val response: Response<Void> = execute()
    if (response.isSuccessful) {
        response.body()?.let { body ->
            return body
        } ?: kotlin.run {
            return Any()
        }
    }
    throw Exception("Response wasn't successful -> $response")
}
fun <T: Any> Call<T>.executeAndDeliver(): T {
    val response: Response<T> = execute()
    if (response.isSuccessful) {
        response.body()?.let { body ->
            // Everything went well, continue
            return body
        } ?: kotlin.run {
            // Success response but body is empty
            throw Exception("Response successful, however the body is empty")
        }
    }
    throw Exception("Response failed -> $response")
}