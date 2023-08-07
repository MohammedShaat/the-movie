package com.example.themovie.presentation.movie_details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.themovie.data.remote.dto.sub.GenreDto
import com.example.themovie.domain.model.sub.Genre
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import com.example.themovie.util.DummyObjects

@Composable
fun GenreItem(
    genre: Genre,
    modifier: Modifier = Modifier,
) {
    Text(
        text = genre.name,
        style = MaterialTheme.typography.labelLarge.copy(
            color = MaterialTheme.colorScheme.secondary,
        ),
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(8.dp)
    )
}

@DefaultPreview
@Composable
fun GenreItemPreview() {
    TheMovieTheme {
        GenreItem(genre = DummyObjects.genre)
    }
}