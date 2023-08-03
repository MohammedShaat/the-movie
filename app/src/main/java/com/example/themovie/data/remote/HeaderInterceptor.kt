package com.example.themovie.data.remote

import com.example.themovie.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header("accept", "application/json")
            .header("Authorization", "Bearer ${Constants.API_KEY}")
            .build()
        return chain.proceed(newRequest)
    }
}