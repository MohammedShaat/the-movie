package com.example.themovie.domain.model

import java.time.LocalDate
import java.util.Date

data class Movie(
    val adult: Boolean,
    val id: Int,
    val posterUrl: String?,
    val title: String,
    val voteAverage: Double,
)