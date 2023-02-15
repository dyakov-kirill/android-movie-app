package com.example.andorid_movie_app.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("docs")
    val movies: List<MovieModel>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)