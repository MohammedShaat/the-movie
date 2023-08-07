package com.example.themovie.domain.model

import com.example.themovie.domain.model.sub.Genre
import java.util.Date

data class MovieDetails(
    val adult: Boolean,
    val backdropUrl: String?,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val overview: String,
    val posterUrl: String?,
    val releaseDate: Date,
    val runtime: Int,
    val status: String,
    val title: String,
    val voteAverage: Double,
)
