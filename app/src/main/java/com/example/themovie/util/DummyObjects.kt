package com.example.themovie.util

import com.example.themovie.data.remote.dto.Genre
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.model.MovieDetails

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

    val genre = Genre(
        id = 1,
        name = "Action"
    )

    val genres = (1..7).map {
        genre.copy(id = it)
    }

    val movieDetails = MovieDetails(
        id = 346698,
        adult = true,
        backdropUrl = "",
        genres = genres,
        homepage = "https://www.barbie-themovie.com",
        overview = "Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.",
        posterUrl = "",
        releaseDate = "2023-07-19".parseToDate,
        runtime = 114,
        status = "Released",
        title = "Spider Man No Way Home",
        voteAverage = 7.497,
    )
}