package com.example.themovie.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.themovie.data.local.MovieEntity
import com.example.themovie.data.local.RemoteKeyEntity
import com.example.themovie.data.local.TheMovieDatabase
import com.example.themovie.data.mapper.toMovieEntity
import com.example.themovie.util.MoviesListType
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val db: TheMovieDatabase,
    private val api: TheMovieApi,
    private val moviesListType: MoviesListType
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

            val response = when (moviesListType) {
                MoviesListType.NOW_PLAYING -> api.getNowPlayingMovies(key)
                MoviesListType.POPULAR -> api.getPopularMovies(key)
                MoviesListType.TOP_RATED -> api.getTopRatedMovies(key)
                MoviesListType.UPCOMING -> api.getUpcomingMovies(key)
            }
            val remoteMovies = response.results.map { it.toMovieEntity() }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieDao.clearAll()
                }
                movieDao.upsertMovies(remoteMovies)
                remoteKeyDao.clearAll()
                remoteKeyDao.upsertRemoteKey(RemoteKeyEntity(response.page + 1))
            }

            MediatorResult.Success(response.page >= response.totalPages)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }
}