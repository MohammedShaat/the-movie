package com.example.themovie.domain.model

data class Movie(
    val adult: Boolean,
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
)
