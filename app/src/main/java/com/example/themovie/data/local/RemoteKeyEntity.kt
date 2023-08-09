package com.example.themovie.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key_table")
data class RemoteKeyEntity(
    @PrimaryKey val nextKey: Int
)