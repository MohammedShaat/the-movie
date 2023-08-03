package com.example.themovie.data.remote.dto


import com.squareup.moshi.Json

data class Genre(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)