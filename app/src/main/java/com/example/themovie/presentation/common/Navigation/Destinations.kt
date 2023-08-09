package com.example.themovie.presentation.common.Navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.themovie.util.Constants.ARG_MOVIE_ID

interface Destination {
    val route: String
    companion object {
        fun getDestination(route: String): Destination {
            val extractedRoute = route.substringBefore("/")
            return theMovieDestinations.find { it.route == extractedRoute } ?: MoviesListDestination
        }
    }
}

object OnboardingDestination : Destination {
    override val route = "onboarding"
}

object MoviesListDestination : Destination {
    override val route = "movies_list"
}

object MovieDetailsDestination : Destination {
    override val route = "movie_details"
    val arguments = listOf(
        navArgument(ARG_MOVIE_ID) { type = NavType.IntType }
    )
    val routeWithArgs = "$route/{$ARG_MOVIE_ID}"
}

val theMovieDestinations = listOf(MoviesListDestination, MovieDetailsDestination)