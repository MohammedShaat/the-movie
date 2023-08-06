package com.example.themovie.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val posterPath: String?,
    val title: String,
    val voteAverage: Double,
)
