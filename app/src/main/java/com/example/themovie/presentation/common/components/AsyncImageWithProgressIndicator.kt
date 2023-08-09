package com.example.themovie.presentation.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@Composable
fun AsyncImageWithProgressIndicator(
    model: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onState: ((AsyncImagePainter.State) -> Unit)? = null,
    showProgressIndicator: Boolean = true,
    contentScale: ContentScale = ContentScale.FillBounds,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        AsyncImage(
            model = model,
            contentDescription = contentDescription,
            onState = onState,
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize(),
        )
        if (showProgressIndicator) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
        }
    }

}

@DefaultPreview
@Composable
fun AsyncImageWithProgressIndicatorPreview() {
    TheMovieTheme {
        AsyncImageWithProgressIndicator(
            model = "",
            modifier = Modifier.size(width = 200.dp, height = 150.dp),
        )
    }
}