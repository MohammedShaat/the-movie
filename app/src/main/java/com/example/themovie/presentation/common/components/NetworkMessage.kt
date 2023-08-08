package com.example.themovie.presentation.common.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.themovie.R
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import java.io.IOException

@Composable
fun NetworkMessage(
    error: Throwable,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (error is IOException) stringResource(R.string.no_connection_br)
        else stringResource(R.string.unexpected_error),
        style = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        ),
        modifier = modifier
    )
}

@Preview
@Composable
fun NetworkMessagePreview() {
    TheMovieTheme {
        NetworkMessage(error = IOException())
    }
}