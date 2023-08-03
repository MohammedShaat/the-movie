package com.example.themovie.domain.model

import com.example.themovie.data.remote.dto.Genre
import com.squareup.moshi.Json

data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val title: String,
    val voteAverage: Double,
)
