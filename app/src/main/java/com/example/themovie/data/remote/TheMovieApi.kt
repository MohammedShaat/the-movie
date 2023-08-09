package com.example.themovie.data.remote

import com.example.themovie.data.remote.dto.MovieDetailsDto
import com.example.themovie.data.remote.dto.MovieImagesDto
import com.example.themovie.data.remote.dto.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {

    @GET("movie/now_playing?language=en-US")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("movie/popular?language=en-US")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("movie/top_rated?language=en-US")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("movie/upcoming?language=en-US")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("search/movie?language=en-US&include_adult=true")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MoviesListResponse

    @GET("movie/{id}?language=en-US")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): MovieDetailsDto

    @GET("movie/{id}/images")
    suspend fun getMovieImages(
        @Path("id") id: Int
    ): MovieImagesDto
}