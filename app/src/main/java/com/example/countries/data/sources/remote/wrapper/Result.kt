package com.example.countries.data.sources.remote.wrapper

sealed class Result<T : Any> {
    class Success<T : Any>(val data: T) : Result<T>()
    class Error<T : Any>(val code: Int, val message: String?) : Result<T>()
    class Failure<T : Any>(val exception: Exception) : Result<T>()
}