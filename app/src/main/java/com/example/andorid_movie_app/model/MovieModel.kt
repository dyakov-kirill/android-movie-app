package com.example.andorid_movie_app.model

data class MovieModel(
    val alternativeName: String,
    val description: String,
    val enName: Any,
    val externalId: ExternalId,
    val id: Int,
    val logo: Logo,
    val movieLength: Int,
    val name: String,
    val names: List<Name>,
    val poster: Poster?,
    val rating: Rating,
    val releaseYears: List<Any>,
    val shortDescription: String,
    val type: String,
    val votes: Votes,
    val watchability: Watchability,
    val year: Int
)