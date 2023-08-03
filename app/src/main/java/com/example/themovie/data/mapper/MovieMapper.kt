package com.example.themovie.data.mapper

import com.example.themovie.data.local.MovieEntity
import com.example.themovie.data.remote.dto.MovieDto
import com.example.themovie.domain.model.Movie

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        adult = adult,
        id = id,
        posterPath = posterPath,
        title = title,
        voteAverage = voteAverage,
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        adult = adult,
        id = id,
        posterPath = posterPath,
        title = title,
        voteAverage = voteAverage,
    )
}