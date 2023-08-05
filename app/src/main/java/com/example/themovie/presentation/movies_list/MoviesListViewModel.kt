package com.example.themovie.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.util.MoviesListType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    val movies = repo.getMovies(MoviesListType.NOW_PLAYING)
        .cachedIn(viewModelScope)
}