package com.example.themovie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.repository.LaunchStateRepository
import com.example.themovie.presentation.common.Navigation.Destination
import com.example.themovie.presentation.common.Navigation.MoviesListDestination
import com.example.themovie.presentation.common.Navigation.OnboardingDestination
import com.example.themovie.util.LaunchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val launchStateRepository: LaunchStateRepository
) : ViewModel() {

    var startDestination: Destination by mutableStateOf(OnboardingDestination)
        private set
    var loading by mutableStateOf(true)
    private set

    init {
        viewModelScope.launch {
            startDestination = when (launchStateRepository.getLaunchState()) {
                LaunchState.NOT_LAUNCHED -> OnboardingDestination
                LaunchState.LAUNCHED -> MoviesListDestination
            }
            delay(600)
            loading = false
        }
    }
}