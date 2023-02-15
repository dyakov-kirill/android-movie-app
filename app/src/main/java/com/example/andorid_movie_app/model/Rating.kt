package com.example.andorid_movie_app.model

data class Rating(
    val _id: String,
    val await: Double,
    val filmCritics: Double,
    val imdb: Double,
    val kp: Double,
    val russianFilmCritics: Double
)