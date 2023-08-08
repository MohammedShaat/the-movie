package com.example.themovie.presentation.movie_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovie.domain.model.MovieDetails
import com.example.themovie.domain.model.MovieImages
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.util.Constants.ARG_MOVIE_ID
import com.example.themovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = requireNotNull(savedStateHandle.get<Int>(ARG_MOVIE_ID)) {
        "The movie id argument not sent to MovieDetailsScreen"
    }

    var movieDetails: Resource<MovieDetails> by mutableStateOf(Resource.Loading())
        private set
    var movieImages: Resource<MovieImages> by mutableStateOf(Resource.Loading())
        private set

    private var movieDetailsJob: Job? = null
    private var movieImagesJob: Job? = null

    init {
        getMovieDetails()
        getMovieImages()
    }


    private fun getMovieDetails() {
        movieDetailsJob?.cancel()
        movieDetailsJob = viewModelScope.launch {
            repo.getMovieDetails(id).collect { result -> movieDetails = result }
        }
    }

    private fun getMovieImages() {
        movieImagesJob?.cancel()
        movieImagesJob = viewModelScope.launch {
            repo.getMovieImages(id).collect { result -> movieImages = result }
        }
    }

    fun onRetry() {
        getMovieDetails()
        getMovieImages()
    }
}