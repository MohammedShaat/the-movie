package com.example.themovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themovie.data.remote.dto.MovieDetailsDto

@Database(
    entities = [MovieEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class TheMovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val remoteKeyDao: RemoteKeyDao
}