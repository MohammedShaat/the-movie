package com.example.themovie.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.onboarding.components.BottomSection
import com.example.themovie.presentation.onboarding.components.PageContent
import com.example.themovie.presentation.onboarding.components.TopSection
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onStartOrSkipClicked: () -> Unit) {
    val onboardingPages by remember { mutableStateOf(onboardingPages) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TopSection(onSkipClicked = onStartOrSkipClicked)

        val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = pagerState
        ) { pageNum ->
            PageContent(page = onboardingPages[pageNum])
        }

        BottomSection(
            count = pagerState.pageCount,
            currentPage = pagerState.currentPage,
            onNextClicked = {
                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
            },
            onStartClicked = onStartOrSkipClicked,
        )
    }
}

@DefaultPreview
@Composable
fun OnboardingScreenPreview() {
    TheMovieTheme {
        OnboardingScreen(onStartOrSkipClicked = {})
    }
}