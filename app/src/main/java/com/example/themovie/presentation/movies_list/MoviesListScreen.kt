package com.example.themovie.presentation.movies_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.themovie.domain.model.Movie
import com.example.themovie.presentation.movies_list.components.MovieItem
import com.example.themovie.presentation.ui.theme.TheMovieTheme


@Composable
fun MoviesListScreen(
    movies: LazyPagingItems<Movie>
) {
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        columns = GridCells.Adaptive(150.dp)
    ) {
        items(count = movies.itemCount) { index ->
            val movie = movies[index]
            if (movie != null) {
                MovieItem(movie = movie)
            }
        }
    }
}

@Preview
@Composable
fun MoviesListScreenPreview() {
    TheMovieTheme {

    }
}