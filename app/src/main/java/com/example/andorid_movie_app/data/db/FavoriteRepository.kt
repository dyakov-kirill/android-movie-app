package com.example.andorid_movie_app.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.andorid_movie_app.model.IdModel

interface FavoriteRepository {
    suspend fun addFavorite(id: String)
    suspend fun delete(id: IdModel)
    suspend fun getAll(id: String): List<IdModel>
}