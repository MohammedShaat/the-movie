package com.example.themovie.util

import android.util.Log
import com.example.themovie.BuildConfig
import java.io.FileInputStream
import java.util.Properties

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val API_KEY = BuildConfig.API_KEY

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"

    const val DATE_PATTERN = "yyyy-MM-dd"

    const val GLOBAL_TAG = "GlobalTag"

    const val ARG_MOVIE_ID = "movie_id"
}