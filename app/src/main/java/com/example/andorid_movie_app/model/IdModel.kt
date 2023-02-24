package com.example.andorid_movie_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

@Entity
data class IdModel(
    @PrimaryKey(autoGenerate = false) var id: Int
)
