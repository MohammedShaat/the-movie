package com.example.themovie.presentation.common.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.themovie.R
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@Composable
fun RetryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.wrapContentSize()
    ) {
        Text(
            text = stringResource(R.string.retry),
        )
    }
}

@DefaultPreview
@Composable
fun RetryButtonPreview() {
    TheMovieTheme {
        RetryButton(onClick = {})
    }
}