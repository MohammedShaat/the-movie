package com.example.themovie.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class RemoteKeyDaoTest {

    @Inject
    @Named("test")
    lateinit var db: TheMovieDatabase
    private lateinit var remoteKeyDao: RemoteKeyDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val remoteKeyEntity = RemoteKeyEntity(1)

    @Before
    fun setupHilt() {
        hiltRule.inject()
        remoteKeyDao = db.remoteKeyDao
    }

    @Test
    fun upsertRemoteKey_remoteKey_insertsRemoteKeyIntoDb() = runBlocking {
        remoteKeyDao.upsertRemoteKey(remoteKeyEntity)

        // Assert that remoteKey is inserted
        val resultRemoteKey = remoteKeyDao.getRemoteKey()
        assertThat(resultRemoteKey, equalTo(remoteKeyEntity))
    }

    @Test
    fun clear_deletesAllKeys() = runBlocking {
        // Insert remoteKey
        remoteKeyDao.upsertRemoteKey(remoteKeyEntity)
        // Assert that remoteKey is inserted
        var resultRemoteKey = remoteKeyDao.getRemoteKey()
        assertThat(resultRemoteKey, equalTo(remoteKeyEntity))

        // Delete remoteKey
        remoteKeyDao.clearAll()
        // Assert that remoteKey is deleted
        resultRemoteKey = remoteKeyDao.getRemoteKey()
        assertThat(resultRemoteKey, nullValue())
    }
}