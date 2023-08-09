package com.example.themovie.data.remote.dto.sub


import com.squareup.moshi.Json

data class GenreDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)