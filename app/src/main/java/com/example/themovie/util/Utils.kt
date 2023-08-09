package com.example.themovie.util

import android.annotation.SuppressLint
import com.example.themovie.util.Constants.DATE_PATTERN
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class MoviesFilter {
    NOW_PLAYING, POPULAR, TOP_RATED, UPCOMING, SEARCH
}

@SuppressLint("ConstantLocale")
val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())

val String.parseToDate: Date
    get() = formatter.parse(this)!!

val Date.format: String
    get() = formatter.format(this)