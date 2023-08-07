package com.example.themovie.data.mapper

import com.example.themovie.data.mapper.sub.toBackdrop
import com.example.themovie.data.remote.dto.MovieImagesDto
import com.example.themovie.domain.model.MovieImages

fun MovieImagesDto.toMovieImages(): MovieImages {
    return MovieImages(
        backdrops = backdrops.map { it.toBackdrop() },
        id = id
    )
}