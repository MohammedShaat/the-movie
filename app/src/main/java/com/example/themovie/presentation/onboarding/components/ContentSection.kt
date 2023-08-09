package com.example.themovie.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.themovie.presentation.common.FullScreenPreview
import com.example.themovie.presentation.onboarding.OnboardingPage
import com.example.themovie.presentation.onboarding.onboardingPages
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@Composable
fun PageContent(
    modifier: Modifier = Modifier,
    page: OnboardingPage,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(shape = MaterialTheme.shapes.small) {
            Image(
                modifier = Modifier.size(width = 300.dp, height = 260.dp),
                painter = painterResource(page.image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(page.title),
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = stringResource(page.description),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            ),
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@FullScreenPreview
@Composable
fun PageContentPreview() {
    TheMovieTheme {
        PageContent(page = onboardingPages.first())
    }
}