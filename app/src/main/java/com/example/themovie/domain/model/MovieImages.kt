package com.example.themovie.domain.model

import com.example.themovie.domain.model.sub.Backdrop

data class MovieImages(
    val backdrops: List<Backdrop>,
    val id: Int,
)
