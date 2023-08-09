package com.example.themovie.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class MovieDaoTest {

    @Inject
    @Named("test")
    lateinit var db: TheMovieDatabase
    private lateinit var movieDao: MovieDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val movies = listOf(
        MovieEntity(
            adult = false, id = 1, posterPath = "", title = "Mission Impossible", voteAverage = 3.5,
        ),
        MovieEntity(
            adult = false, id = 2, posterPath = "", title = "Spider Man", voteAverage = 7.2,
        ),
        MovieEntity(
            adult = false, id = 3, posterPath = "", title = "Extraction", voteAverage = 10.0,
        ),
    )

    @Before
    fun setupHilt() {
        hiltRule.inject()
        movieDao = db.movieDao
    }

    @Test
    fun pagingSource_moviesList_insertsMovies(): Unit = runBlocking {
        movieDao.upsertMovies(movies)

        // Assert that movies are inserted
        val pagingSource = movieDao.pagingSource()
        val loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(null, loadSize = 20, placeholdersEnabled = false)
        ) as LoadResult.Page

        assertThat(loadResult.data, containsInAnyOrder(*movies.toTypedArray()))
    }

    @Test
    fun clearAll_deletesAllMovies(): Unit = runBlocking {
        // Insert movies
        movieDao.upsertMovies(movies)
        // Assert that movies are inserted
        val pagingSource = movieDao.pagingSource()
        var loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(null, loadSize = 20, placeholdersEnabled = false)
        )
        assertThat(loadResult, instanceOf(LoadResult.Page::class.java))
        assertThat((loadResult as LoadResult.Page).data, containsInAnyOrder(*movies.toTypedArray()))

        // Delete movies
        movieDao.clearAll()
        // Assert that movies are deleted
        loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(null, loadSize = 20, placeholdersEnabled = false)
        )
        assertThat(loadResult, instanceOf(LoadResult.Invalid::class.java))
    }
}