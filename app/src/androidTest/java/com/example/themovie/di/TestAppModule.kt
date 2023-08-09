package com.example.themovie.di

import android.app.Application
import androidx.room.Room
import com.example.themovie.data.local.TheMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    @Named("test")
    fun provideTheMovieDatabase(app: Application): TheMovieDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            TheMovieDatabase::class.java,
        )
            .build()
    }

}