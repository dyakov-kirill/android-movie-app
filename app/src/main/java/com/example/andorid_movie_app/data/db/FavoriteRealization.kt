package com.example.andorid_movie_app.data.db

import com.example.andorid_movie_app.model.IdModel

class FavoriteRealization(private val dao: FavoriteDao) : FavoriteRepository {
    override suspend fun addFavorite(id: String) {
        dao.addFavorite(IdModel(id.toInt()))
    }

    override suspend fun delete(id: IdModel) {
        return dao.deleteFavorite(id.id)
    }

    override suspend fun getAll(id: String): List<IdModel> {
        TODO("Not yet implemented")
    }
}