package com.example.themovie.presentation.movie_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.example.themovie.R
import com.example.themovie.domain.model.MovieDetails
import com.example.themovie.presentation.common.FullScreenPreview
import com.example.themovie.presentation.common.components.AsyncImageWithProgressIndicator
import com.example.themovie.presentation.movie_details.components.MovieDetailsTopAppBar
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import com.example.themovie.util.DummyObjects
import com.example.themovie.util.format

@Composable
fun MovieDetailsScreen(
    onNavigateUpClicked: () -> Unit,
    movieDetails: MovieDetails = DummyObjects.movieDetails,
//    viewModel: MoveDetailsViewModel = hiltViewModel()
) {
    var backdropLoading by remember { mutableStateOf(false) }
    var posterLoading by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        MovieDetailsTopAppBar(onNavigateUpClicked = onNavigateUpClicked)

        // Backdrop, poster & title
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Backdrop
            AsyncImageWithProgressIndicator(
                model = movieDetails.backdropUrl,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(200.dp),
                contentDescription = stringResource(R.string.cd_movie_backdrop),
                onState = { state ->
                    backdropLoading = state is AsyncImagePainter.State.Loading
                },
                showProgressIndicator = backdropLoading,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(top = 150.dp, start = 24.dp, end = 24.dp),
            ) {
                // Poster
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp)
                ) {
                    AsyncImageWithProgressIndicator(
                        model = movieDetails.backdropUrl,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = stringResource(R.string.cd_movie_poster),
                        onState = { state ->
                            posterLoading = state is AsyncImagePainter.State.Loading
                        },
                        showProgressIndicator = posterLoading,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                // Title
                Text(
                    text = movieDetails.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                )
            }
        }//Backdrop, poster & title
        Spacer(modifier = Modifier.height(24.dp))

        // Release date, time, vote avg, & only adults
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // Release date
            Row {
                Icon(painter = painterResource(R.drawable.ic_calendar), contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = movieDetails.releaseDate.format)
            }

            // Time
            Row {
                Icon(painter = painterResource(R.drawable.ic_clock), contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(R.string.movie_time, movieDetails.runtime))
            }

            // Vote avg
            Row {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.movie_vote_avg, movieDetails.voteAverage),
                    color = Color.Yellow
                )
            }

            // Only adults
            if (movieDetails.adult) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_adults_only),
                        contentDescription = stringResource(R.string.cd_for_adults),
                        modifier = Modifier.size(26.dp),
                    )
                }
            }
        }// Release date, time & vote


    }
}

@FullScreenPreview
@Composable
fun MovieDetailsScreenPreview() {
    TheMovieTheme {
        MovieDetailsScreen(
            onNavigateUpClicked = {}
        )
    }
}