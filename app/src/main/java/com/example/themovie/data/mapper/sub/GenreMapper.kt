package com.example.themovie.data.mapper.sub

import com.example.themovie.data.remote.dto.sub.GenreDto
import com.example.themovie.domain.model.sub.Genre

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name
    )
}