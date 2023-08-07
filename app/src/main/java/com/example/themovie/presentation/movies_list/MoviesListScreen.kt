package com.example.themovie.presentation.movies_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.themovie.R
import com.example.themovie.domain.model.Movie
import com.example.themovie.presentation.movies_list.components.FAB
import com.example.themovie.presentation.movies_list.components.MovieItem
import com.example.themovie.presentation.movies_list.components.MoviesFilterList
import com.example.themovie.presentation.movies_list.components.SearchBar
import kotlinx.coroutines.launch
import java.io.IOException


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

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            value = viewModel.search,
            onValueChange = viewModel::onSearchChanged,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        MoviesFilterList(
            selectedItem = viewModel.moviesFilter,
            onItemSelected = viewModel::onMovieFilterChanged
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyVerticalGrid(
                state = lazyGridState,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                columns = GridCells.Adaptive(150.dp)
            ) {
                items(count = movies.itemCount, key = { movies[it]?.id ?: it }) { index ->
                    val movie = movies[index]
                    if (movie != null) {
                        MovieItem(movie = movie, onClick = onMovieClicked)
                    }
                }

                // Appending indicator
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
            }

            // Initial loading and refresh indicator
            if (movies.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator()
            }

            // Error with no data indicator
            if (movies.loadState.refresh is LoadState.Error && movies.itemCount == 0) {
                val error = (movies.loadState.refresh as LoadState.Error).error
                Text(
                    text = if (error is IOException) stringResource(R.string.no_connection)
                    else stringResource(R.string.unexpected_error),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    ),
                )
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
        }
    }
}