package com.example.themovie.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.themovie.data.local.MovieEntity
import com.example.themovie.data.remote.dto.MovieDto
import com.example.themovie.domain.model.Movie
import com.example.themovie.util.Constants.IMAGE_BASE_URL

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
        posterUrl = posterPath?.let { "$IMAGE_BASE_URL$posterPath" },
        title = title,
        voteAverage = voteAverage,
    )
}