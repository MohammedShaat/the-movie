package com.example.themovie.data.remote.dto.sub


import com.squareup.moshi.Json

data class BackdropDto(
    @field:Json(name = "file_path") val filePath: String,
)