package com.example.themovie.presentation.movie_details.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.example.themovie.R
import com.example.themovie.domain.model.sub.Backdrop
import com.example.themovie.presentation.common.components.AsyncImageWithProgressIndicator
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import com.example.themovie.util.DummyObjects

@Composable
fun MovieBackdropItem(
    backdrop: Backdrop,
    modifier: Modifier = Modifier
) {
    var loading by remember { mutableStateOf(false) }
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .size(width = 250.dp, height = 150.dp)
    ) {
        AsyncImageWithProgressIndicator(
            model = backdrop.url,
            onState = { loading = it is AsyncImagePainter.State.Loading },
            showProgressIndicator = loading,
            contentDescription = stringResource(R.string.cd_one_of_movie_backdrops),
        )
    }
}

@Preview
@Composable
fun MovieBackdropItemPreview() {
    TheMovieTheme {
        MovieBackdropItem(backdrop = DummyObjects.backdrop)
    }
}