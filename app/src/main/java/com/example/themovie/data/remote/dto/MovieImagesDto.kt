package com.example.themovie.data.remote.dto


import com.example.themovie.data.remote.dto.sub.BackdropDto
import com.squareup.moshi.Json

data class MovieImagesDto(
    @field:Json(name = "backdrops") val backdrops: List<BackdropDto>,
    @field:Json(name = "id") val id: Int,
)