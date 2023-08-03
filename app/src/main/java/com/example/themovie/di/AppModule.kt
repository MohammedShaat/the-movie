package com.example.themovie.di

import android.app.Application
import androidx.room.Room
import com.example.themovie.data.local.TheMovieDatabase
import com.example.themovie.data.remote.HeaderInterceptor
import com.example.themovie.data.remote.TheMovieApi
import com.example.themovie.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTheMovieDatabase(app: Application): TheMovieDatabase {
        return Room.databaseBuilder(
            app,
            TheMovieDatabase::class.java,
            "the_movie.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideTheMovieApi(okHttpClient: OkHttpClient): TheMovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}