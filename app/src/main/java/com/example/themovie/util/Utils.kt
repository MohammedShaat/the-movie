package com.example.themovie.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.themovie.util.Constants.DATE_PATTERN
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

enum class MoviesListType {
    NOW_PLAYING, POPULAR, TOP_RATED, UPCOMING
}