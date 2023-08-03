package com.example.themovie.util

sealed class Resource<T>(open val data: T? = null, open val error: Exception? = null) {
    class Success<T>(override val data: T) : Resource<T>(data)
    class Loading<T>(data: T?) : Resource<T>(data)
    class Error<T>(override val error: Exception, data: T?) : Resource<T>(data, error)
}