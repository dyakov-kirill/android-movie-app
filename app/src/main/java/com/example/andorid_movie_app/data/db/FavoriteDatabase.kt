package com.example.andorid_movie_app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.andorid_movie_app.model.IdModel

@Database(entities = [IdModel::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun getTaskDao(): FavoriteDao

    companion object {
        private var database: FavoriteDatabase ?= null
        @Synchronized
        fun getInstance(context: Context): FavoriteDatabase =
            if (database == null) {
                database = Room.databaseBuilder(context, FavoriteDatabase::class.java, "task_database").build()
                database as FavoriteDatabase
            } else {
                database as FavoriteDatabase
            }
    }
}