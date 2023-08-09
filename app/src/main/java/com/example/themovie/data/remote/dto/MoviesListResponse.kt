package com.example.themovie.data.remote.dto


import com.squareup.moshi.Json

data class MoviesListResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<MovieDto>,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int
)