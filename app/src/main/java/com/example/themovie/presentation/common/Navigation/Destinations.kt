package com.example.themovie.presentation.common.Navigation

interface Destination {
    val route: String
}

object MoviesListDestination : Destination {
    override val route = "movies_list"
}