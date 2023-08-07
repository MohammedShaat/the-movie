package com.example.themovie.domain.repository

import androidx.paging.PagingData
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.model.MovieDetails
import com.example.themovie.util.MoviesFilter
import com.example.themovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(moviesFilter: MoviesFilter, query: String = ""): Flow<PagingData<Movie>>

    fun getMovieDetails(id: Int): Flow<Resource<MovieDetails>>
}