package com.example.andorid_movie_app.data.db

import androidx.room.*
import com.example.andorid_movie_app.model.IdModel

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(id: IdModel)
    @Query("SELECT * FROM IdModel WHERE id=:id")
    fun getById(id: Int): IdModel?
    @Query("SELECT * FROM IdModel")
    suspend fun getAll(): List<IdModel>
    @Query("DELETE FROM IdModel WHERE id=:id")
    suspend fun deleteFavorite(id: Int)
}