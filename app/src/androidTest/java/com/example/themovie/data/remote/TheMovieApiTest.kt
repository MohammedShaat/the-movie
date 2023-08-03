package com.example.themovie.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class TheMovieApiTest {

    @Inject
    lateinit var api: TheMovieApi

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setupHilt() {
        hiltRule.inject()
    }

    @Test
    fun getNowPlayingMovies_returnsMoviesList(): Unit = runBlocking {
        val response = api.getNowPlayingMovies(1)

        // Assert that there are movies
        assertThat(response.results, not(empty()))
    }

    @Test
    fun getPopularMovies_returnsMoviesList(): Unit = runBlocking {
        val response = api.getPopularMovies(1)

        // Assert that there are movies
        assertThat(response.results, not(empty()))
    }

    @Test
    fun getTopRatedMovies_returnsMoviesList(): Unit = runBlocking {
        val response = api.getTopRatedMovies(1)

        // Assert that there are movies
        assertThat(response.results, not(empty()))
    }

    @Test
    fun getUpcomingMovies_returnsMoviesList(): Unit = runBlocking {
        val response = api.getUpcomingMovies(1)

        // Assert that there are movies
        assertThat(response.results, not(empty()))
    }
}