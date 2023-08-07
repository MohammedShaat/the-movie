package com.example.themovie.presentation.movie_details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.themovie.R
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.common.FullScreenPreview
import com.example.themovie.presentation.movie_details.MovieDetailsScreen
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopAppBar(onNavigateUpClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.screen_title_details),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateUpClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.cd_navigate_up)
                )
            }
        },
        actions = {
            Icon(
                painter = painterResource(R.drawable.ic_movie),
                contentDescription = stringResource(R.string.cd_movie_icon)
            )
            Spacer(modifier = Modifier.width(16.dp))
        },
    )

}

@DefaultPreview
@Composable
fun MovieDetailsScreenPreview() {
    TheMovieTheme {
        MovieDetailsTopAppBar(
            onNavigateUpClicked = {}
        )
    }
}