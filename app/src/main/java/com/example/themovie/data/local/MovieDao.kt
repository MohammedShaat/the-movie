package com.example.themovie.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Upsert
    fun upsertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movie_table")
    fun clearAll()
}