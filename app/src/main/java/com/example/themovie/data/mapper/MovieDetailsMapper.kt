package com.example.themovie.data.mapper

import com.example.themovie.data.mapper.sub.toGenre
import com.example.themovie.data.remote.dto.MovieDetailsDto
import com.example.themovie.domain.model.MovieDetails
import com.example.themovie.util.Constants.IMAGE_BASE_URL
import com.example.themovie.util.parseToDate

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult = adult,
        backdropUrl = backdropPath?.let { "$IMAGE_BASE_URL/$it" },
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = id,
        overview = overview,
        posterUrl = posterPath?.let { "$IMAGE_BASE_URL/$it" },
        releaseDate = releaseDate.parseToDate,
        runtime = runtime,
        status = status,
        title = title,
        voteAverage = voteAverage,
    )
}