package com.example.themovie.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.util.Constants.ARG_MOVIE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repo: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = requireNotNull(savedStateHandle.get<Int>(ARG_MOVIE_ID)) {
        "The movie id argument not sent to MovieDetailsScreen"
    }

    val movieDetailsFlow = repo.getMovieDetails(id)
    val movieImagesFlow = repo.getMovieImages(id)
}