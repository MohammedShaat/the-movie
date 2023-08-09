package com.example.themovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.themovie.presentation.common.Navigation.Destination
import com.example.themovie.presentation.common.Navigation.MoviesListDestination
import com.example.themovie.presentation.common.Navigation.TheMovieNavHost
import com.example.themovie.presentation.common.Navigation.theMovieDestinations
import com.example.themovie.presentation.movies_list.components.FAB
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.loading
        }

        setContent {
            App(viewModel.startDestination)
        }
    }
}

@Composable
fun App(startDestination: Destination) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination =
        Destination.getDestination(currentBackStackEntry?.destination?.route ?: "")

    val lazyGridState = rememberLazyGridState()
    val scrolledDown by remember {
        derivedStateOf { lazyGridState.firstVisibleItemIndex != 0 }
    }


    TheMovieTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            backgroundColor = MaterialTheme.colorScheme.background,
            floatingActionButton = {
                AnimatedVisibility(scrolledDown && currentDestination is MoviesListDestination) {
                    FAB(onClick = { scope.launch { lazyGridState.scrollToItem(0) } })
                }
            }
        ) { contentPadding ->
            TheMovieNavHost(
                modifier = Modifier.padding(contentPadding),
                navController = navController,
                lazyGridState = lazyGridState,
                startDestination = startDestination,
                onNoConnection = { message, actionLabel, action ->
                    scope.launch {
                        // To avoid queuing multiple snackbars
                        if (scaffoldState.snackbarHostState.currentSnackbarData != null)
                            return@launch

                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                            message, actionLabel, SnackbarDuration.Indefinite
                        )
                        if (snackbarResult == SnackbarResult.ActionPerformed)
                            action()
                    }
                },
            )
        }
    }
}