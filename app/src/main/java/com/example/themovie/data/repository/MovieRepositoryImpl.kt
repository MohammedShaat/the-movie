package com.example.themovie.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.themovie.data.local.TheMovieDatabase
import com.example.themovie.data.mapper.toMovie
import com.example.themovie.data.remote.MovieRemoteMediator
import com.example.themovie.data.remote.TheMovieApi
import com.example.themovie.domain.model.Movie
import com.example.themovie.domain.repository.MovieRepository
import com.example.themovie.util.MoviesFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val api: TheMovieApi,
    private val db: TheMovieDatabase
) : MovieRepository {

    private val movieDao = db.movieDao
    private val remoteKeDao = db.remoteKeyDao

    override fun getMovies(moviesFilter: MoviesFilter, query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieRemoteMediator(db, api, moviesFilter, query),
            pagingSourceFactory = { movieDao.pagingSource() }
        )
            .flow
            .map { pagingData ->
                pagingData.map { movieEntity ->
                    movieEntity.toMovie()
                }
            }
    }

//    override fun getMovieDetails(id: Int): Flow<Resource<MovieDetails>> {
//        TODO("Not yet implemented")
//    }
}