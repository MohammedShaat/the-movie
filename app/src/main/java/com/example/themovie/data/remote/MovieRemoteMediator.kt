package com.example.themovie.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.themovie.data.local.MovieEntity
import com.example.themovie.data.local.RemoteKeyEntity
import com.example.themovie.data.local.TheMovieDatabase
import com.example.themovie.data.mapper.toMovieEntity
import com.example.themovie.util.Constants.GLOBAL_TAG
import com.example.themovie.util.MoviesFilter
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val db: TheMovieDatabase,
    private val api: TheMovieApi,
    private val moviesFilter: MoviesFilter,
    private val query: String,
) : RemoteMediator<Int, MovieEntity>() {

    private val remoteKeyDao = db.remoteKeyDao
    private val movieDao = db.movieDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val key = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> remoteKeyDao.getRemoteKey().nextKey
            }

            val response = when (moviesFilter) {
                MoviesFilter.NOW_PLAYING -> api.getNowPlayingMovies(key)
                MoviesFilter.POPULAR -> api.getPopularMovies(key)
                MoviesFilter.TOP_RATED -> api.getTopRatedMovies(key)
                MoviesFilter.UPCOMING -> api.getUpcomingMovies(key)
                MoviesFilter.SEARCH -> api.search(query, key)
            }
            val movies = response.results.map { it.toMovieEntity() }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDao.clearAll()
                }
                movieDao.upsertMovies(movies)
                remoteKeyDao.clearAll()
                remoteKeyDao.upsertRemoteKey(RemoteKeyEntity(response.page + 1))
            }

            MediatorResult.Success(response.page >= response.totalPages)
        } catch (e: HttpException) {
            Log.e(GLOBAL_TAG, "Mediator:: HttpException=$e")
            MediatorResult.Error(e)
        } catch (e: IOException) {
            Log.e(GLOBAL_TAG, "Mediator:: IOException=$e")
            MediatorResult.Error(e)
        }
    }
}