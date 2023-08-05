package com.example.themovie.presentation.common.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.themovie.presentation.movies_list.MoviesListScreen
import com.example.themovie.presentation.movies_list.MoviesListViewModel

@Composable
fun TheMovieNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MoviesListDestination.route,
    ) {
        composable(MoviesListDestination.route) {
            val viewModel: MoviesListViewModel = hiltViewModel()
            val movies = viewModel.movies.collectAsLazyPagingItems()
            MoviesListScreen(movies)
        }
    }
}