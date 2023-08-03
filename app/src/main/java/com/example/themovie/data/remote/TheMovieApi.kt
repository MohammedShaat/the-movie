package com.example.themovie.data.remote

import com.example.themovie.data.remote.dto.MovieDetailsDto
import com.example.themovie.data.remote.dto.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET("now_playing?language=en-US")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("popular?language=en-US")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("top_rated?language=en-US")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("upcoming?language=en-US")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("{id}?language=en-US")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsDto
}