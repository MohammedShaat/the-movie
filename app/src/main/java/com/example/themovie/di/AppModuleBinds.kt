package com.example.themovie.di

import com.example.themovie.data.repository.LaunchStateRepositoryImpl
import com.example.themovie.data.repository.MovieRepositoryImpl
import com.example.themovie.domain.repository.LaunchStateRepository
import com.example.themovie.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun provideLaunchStateRepository(
        launchStateRepositoryImpl: LaunchStateRepositoryImpl
    ): LaunchStateRepository
}