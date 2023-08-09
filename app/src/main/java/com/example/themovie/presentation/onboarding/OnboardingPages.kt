package com.example.themovie.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.themovie.R

data class OnboardingPage(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int,
)

val onboardingPages = listOf(
    OnboardingPage(
        title = R.string.onboarding_title_1,
        description = R.string.onboarding_description_1,
        image = R.drawable.backdrop
    ),
    OnboardingPage(
        title = R.string.onboarding_title_2,
        description = R.string.onboarding_description_2,
        image = R.drawable.poster
    ),
)