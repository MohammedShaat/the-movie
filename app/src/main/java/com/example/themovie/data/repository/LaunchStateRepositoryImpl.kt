package com.example.themovie.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.themovie.domain.repository.LaunchStateRepository
import com.example.themovie.util.LaunchState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.launchDataStore: DataStore<Preferences> by preferencesDataStore("launch_state")

@Singleton
class LaunchStateRepositoryImpl @Inject constructor(@ApplicationContext context: Context) :
    LaunchStateRepository {

    private val dataStore = context.launchDataStore
    private val launchStateFlow = dataStore.data
        .catch { e ->
            e.printStackTrace()
            emptyPreferences()
        }.map { preferences ->
            val launchState = if (preferences[launchedKey] == true) LaunchState.LAUNCHED
            else LaunchState.NOT_LAUNCHED
            launchState
        }

    companion object Keys {
        val launchedKey = booleanPreferencesKey("launched")
    }

    override suspend fun getLaunchState(): LaunchState {
        return launchStateFlow.first()
    }

    override suspend fun setLaunchState(launchState: LaunchState) {
        dataStore.edit { preferences ->
            preferences[launchedKey] = launchState == LaunchState.LAUNCHED
        }
    }
}