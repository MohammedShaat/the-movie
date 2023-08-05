package com.example.themovie.util

import com.example.themovie.domain.model.Movie

object DummyObjects {

    val movie = Movie(
        adult = true,
        id = 1,
        posterUrl = "/uiFcFIjig0YwyNmhoxkxtAAVIL2.jpg",
        title = "mission: impossible - dead reckoning part one",
        voteAverage = 4.4,
    )

    val movies = (1..20).map {
        movie.copy(id = it)
    }
}