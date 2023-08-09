package com.example.themovie.presentation.common.Navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.repository.LaunchStateRepository
import com.example.themovie.util.LaunchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheMovieNavHostViewModel @Inject constructor(
    private val launchStateRepository: LaunchStateRepository
) : ViewModel() {

    fun onStart() = viewModelScope.launch {
        launchStateRepository.setLaunchState(LaunchState.LAUNCHED)
    }
}