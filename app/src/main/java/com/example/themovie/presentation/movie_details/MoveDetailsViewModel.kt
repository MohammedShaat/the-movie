package com.example.themovie.presentation.movie_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.model.MovieDetails
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.util.Constants.ARG_MOVIE_ID
import com.example.themovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveDetailsViewModel @Inject constructor(
    private val repo: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = requireNotNull(savedStateHandle.get<Int>(ARG_MOVIE_ID)) {
        "The movie id argument not sent to MovieDetailsScreen"
    }

    var movieDetails by mutableStateOf<MovieDetails?>(null)
        private set
    var loading by mutableStateOf(false)
        private set
    var error by mutableStateOf<Exception?>(null)
        private set

    init {
        getMovieDetails()
    }


    private fun getMovieDetails() = viewModelScope.launch {
        repo.getMovieDetails(id).collect { result ->
            when (result) {
                is Resource.Success -> {
                    movieDetails = result.data
                }

                is Resource.Loading -> {
                    loading = true
                }

                is Resource.Error -> {
                    error = result.error
                }
            }
        }
    }
}