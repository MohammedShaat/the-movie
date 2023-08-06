package com.example.themovie.presentation.movies_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.util.MoviesFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    var movies = repo.getMovies(MoviesFilter.NOW_PLAYING).cachedIn(viewModelScope)
        private set
    var moviesFilter by mutableStateOf(MoviesFilter.NOW_PLAYING)
        private set
    var search by mutableStateOf("")
        private set

    private var searchJob: Job? = null

    fun onMovieFilterChanged(newMoviesFilter: MoviesFilter) {
        moviesFilter = newMoviesFilter
        search = ""
        movies = repo.getMovies(moviesFilter)
    }

    fun onSearchChanged(newQuery: String) {
        moviesFilter = MoviesFilter.SEARCH
        search = newQuery
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            movies = repo.getMovies(moviesFilter, search)
        }
    }
}