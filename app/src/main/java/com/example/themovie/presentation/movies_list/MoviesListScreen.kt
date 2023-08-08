package com.example.themovie.presentation.movies_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.themovie.R
import com.example.themovie.domain.model.Movie
import com.example.themovie.presentation.common.components.NetworkMessage
import com.example.themovie.presentation.common.components.RetryButton
import com.example.themovie.presentation.movies_list.components.FAB
import com.example.themovie.presentation.movies_list.components.MovieItem
import com.example.themovie.presentation.movies_list.components.MoviesFilterList
import com.example.themovie.presentation.movies_list.components.SearchBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesListScreen(
    onMovieClicked: (Movie) -> Unit,
    viewModel: MoviesListViewModel = hiltViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    val scope = rememberCoroutineScope()

    val lazyGridState = rememberLazyGridState()
    val isScrolledDown by remember {
        derivedStateOf { lazyGridState.firstVisibleItemIndex != 0 }
    }

    val refreshing by remember(movies.loadState) {
        mutableStateOf(movies.loadState.refresh is LoadState.Loading)
    }
    val pullRefreshState =
        rememberPullRefreshState(refreshing = refreshing, onRefresh = movies::refresh)


    Column(modifier = Modifier.fillMaxSize()) {
        // Search
        SearchBar(
            value = viewModel.search,
            onValueChange = viewModel::onSearchChanged,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )


        // Filter
        MoviesFilterList(
            selectedItem = viewModel.moviesFilter,
            onItemSelected = viewModel::onMovieFilterChanged
        )
        Spacer(modifier = Modifier.height(8.dp))


        // Movies, handling loading state & FAB
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState),
        ) {
            // Movies list
            LazyVerticalGrid(
                state = lazyGridState,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                columns = GridCells.Adaptive(150.dp),
            ) {
                items(
                    count = movies.itemCount,
                    key = movies.itemKey { movie -> movie.id }) { index ->
                    val movie = movies[index]
                    if (movie != null) {
                        MovieItem(movie = movie, onClick = onMovieClicked)
                    }
                }

                // Appending is loading
                if (movies.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = stringResource(R.string.loading),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
                // Appending failed
                if (movies.loadState.append is LoadState.Error) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            NetworkMessage(error = (movies.loadState.append as LoadState.Error).error)
                            Spacer(modifier = Modifier.height(16.dp))
                            RetryButton(onClick = { movies.retry() })
                        }
                    }
                }
            }// Movies list

            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )


            // Refresh is loading
//            if (movies.loadState.refresh is LoadState.Loading) {
//                CircularProgressIndicator()
//            }

            // Refresh failed
            if (movies.loadState.refresh is LoadState.Error) {
                when {
                    // No data
                    movies.itemCount == 0 -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            NetworkMessage(error = (movies.loadState.refresh as LoadState.Error).error)
                            Spacer(modifier = Modifier.height(16.dp))
                            RetryButton(onClick = { movies.retry() })
                        }
                    }// No data

                    // There is data
                    else -> {
//                        Snackbar(
//                            action = {
//                                Text(
//                                    text = stringResource(R.string.no_connection_br),
//                                    fontWeight = FontWeight.Bold,
//                                    modifier = Modifier.clickable { movies.retry() }
//                                )
//                            },
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp)
//                                .align(Alignment.BottomCenter)
//                        ) {
//                            Text(
//                                text = stringResource(R.string.no_connection),
//                                modifier = Modifier.fillMaxWidth()
//                            )
//                        }
                    }// There is data
                }
            }


            // Scroll up FAB
            this@Column.AnimatedVisibility(
                visible = isScrolledDown,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                FAB(
                    onClick = { scope.launch { lazyGridState.scrollToItem(0) } },
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }// Movies, handling loading state & FAB
    }
}