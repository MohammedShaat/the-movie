package com.example.themovie.domain.repository

import com.example.themovie.util.LaunchState

interface LaunchStateRepository {

    suspend fun getLaunchState(): LaunchState

    suspend fun setLaunchState(launchState: LaunchState)
}