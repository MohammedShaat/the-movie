package com.example.themovie.presentation.movie_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import com.example.themovie.R
import com.example.themovie.presentation.common.FullScreenPreview
import com.example.themovie.presentation.common.components.AsyncImageWithProgressIndicator
import com.example.themovie.presentation.movie_details.components.GenreItem
import com.example.themovie.presentation.movie_details.components.MovieDetailsTopAppBar
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import com.example.themovie.util.format
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(
    onNavigateUpClicked: () -> Unit,
    viewModel: MoveDetailsViewModel = hiltViewModel()
//    movieDetails: MovieDetails = DummyObjects.movieDetails,
) {
    val movieDetails = viewModel.movieDetails
    var backdropLoading by remember { mutableStateOf(false) }
    var posterLoading by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        // TopAppBar
        MovieDetailsTopAppBar(onNavigateUpClicked = onNavigateUpClicked)

        // Movie details
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            movieDetails?.let {
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
                            shadowElevation = 4.dp,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .width(150.dp)
                                .height(200.dp)
                        ) {
                            AsyncImageWithProgressIndicator(
                                model = movieDetails.posterUrl,
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
                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = movieDetails.releaseDate.format)
                    }

                    // Time
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.ic_clock),
                            contentDescription = null
                        )
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
                            text = stringResource(
                                R.string.movie_vote_avg,
                                movieDetails.voteAverage
                            ),
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
                Spacer(modifier = Modifier.height(24.dp))


                // Genres
                Text(
                    text = stringResource(R.string.genres),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    mainAxisSpacing = 16.dp,
                    crossAxisSpacing = 16.dp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                ) {
                    movieDetails.genres.forEach { genre ->
                        GenreItem(genre = genre)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))


                // Overview
                Text(
                    text = stringResource(R.string.overview),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movieDetails.overview,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
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