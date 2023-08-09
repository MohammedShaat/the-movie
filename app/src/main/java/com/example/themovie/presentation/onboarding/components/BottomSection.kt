package com.example.themovie.presentation.onboarding.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themovie.R
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    count: Int,
    currentPage: Int,
    onNextClicked: () -> Unit,
    onStartClicked: () -> Unit,
) {
    val lastPage = currentPage == count - 1

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        PagerIndicator(count = count, currentPage = currentPage)
        Spacer(modifier = Modifier.height(60.dp))

        Button(onClick = if (lastPage) onStartClicked else onNextClicked) {
            Text(
                text = if (lastPage) stringResource(R.string.start)
                else stringResource(R.string.next)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@DefaultPreview
@Composable
fun BottomSectionPreview() {
    TheMovieTheme {
        BottomSection(count = 3, currentPage = 1, onStartClicked = {}, onNextClicked = {})
    }
}