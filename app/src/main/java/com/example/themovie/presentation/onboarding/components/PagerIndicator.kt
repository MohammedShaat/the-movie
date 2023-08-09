package com.example.themovie.presentation.onboarding.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.ui.theme.Onyx
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    currentPage: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(count) {
            Dot(
                selected = currentPage == it,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@DefaultPreview
@Composable
fun PagerIndicatorPreview() {
    TheMovieTheme {
        PagerIndicator(count = 3, currentPage = 2)
    }
}


@Composable
private fun Dot(
    modifier: Modifier = Modifier,
    selected: Boolean
) {
    val width by animateDpAsState(
        targetValue = if (selected) 35.dp else 15.dp
    )
    Box(
        modifier = modifier
            .height(15.dp)
            .width(width)
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary else Onyx,
                shape = MaterialTheme.shapes.extraLarge
            ),
    )
}

@DefaultPreview
@Composable
private fun DotPreview() {
    TheMovieTheme {
        Dot(selected = false, modifier = Modifier.padding(8.dp))
    }
}