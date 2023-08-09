package com.example.themovie.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RemoteKeyDao {

    @Upsert
    suspend fun upsertRemoteKey(key: RemoteKeyEntity)

    @Query("SELECT * FROM remote_key_table")
    suspend fun getRemoteKey(): RemoteKeyEntity

    @Query("DELETE FROM remote_key_table")
    suspend fun clearAll()
}