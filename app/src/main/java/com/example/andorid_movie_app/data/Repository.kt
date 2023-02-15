package com.example.andorid_movie_app.data

import com.example.andorid_movie_app.model.MovieList
import retrofit2.Response

class Repository {
    suspend fun getMovie() : Response<MovieList> {
        return RetrofitInstance.api.getMovie()
    }

    suspend fun getMovies() : Response<MovieList> {
        return RetrofitInstance.api.getMovies()
    }

    suspend fun getNew() : Response<MovieList> {
        return RetrofitInstance.api.getNew("2020-2023", "year", "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06")
    }
}