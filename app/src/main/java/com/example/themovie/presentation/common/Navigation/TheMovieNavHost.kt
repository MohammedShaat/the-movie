package com.example.themovie.presentation.common.Navigation

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.themovie.presentation.movie_details.MovieDetailsScreen
import com.example.themovie.presentation.movies_list.MoviesListScreen
import com.example.themovie.presentation.onboarding.OnboardingScreen

@Composable
fun TheMovieNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    onNoConnection: (message: String, actionLabel: String, action: () -> Unit) -> Unit,
    startDestination: Destination,
    viewModel: TheMovieNavHostViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        composable(OnboardingDestination.route) {
            OnboardingScreen(
                onStartOrSkipClicked = {
                    viewModel.onStart()
                    navController.popBackStack()
                    navController.navigate(MoviesListDestination.route)
                }
            )
        }

        composable(MoviesListDestination.route) {
            MoviesListScreen(
                onMovieClicked = { movie ->
                    navController.navigate("${MovieDetailsDestination.route}/${movie.id}")
                },
                onNoConnection = onNoConnection,
                lazyGridState = lazyGridState
            )
        }

        composable(
            route = MovieDetailsDestination.routeWithArgs,
            arguments = MovieDetailsDestination.arguments
        ) {
            MovieDetailsScreen(
                onNavigateUpClicked = { navController.navigateUp() }
            )
        }
    }
}