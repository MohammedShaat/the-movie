package com.example.themovie.presentation.common.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.themovie.presentation.movies_list.MoviesListScreen

@Composable
fun TheMovieNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MoviesListDestination.route,
    ) {
        composable(MoviesListDestination.route) {
            MoviesListScreen()
        }
    }
}