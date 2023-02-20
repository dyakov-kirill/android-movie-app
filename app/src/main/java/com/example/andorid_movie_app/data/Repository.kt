package com.example.andorid_movie_app.data

import com.example.andorid_movie_app.model.MovieList
import com.example.andorid_movie_app.API_KEY
import okio.Utf8
import retrofit2.Response

class Repository {
    suspend fun getMovie() : Response<MovieList> {
        return RetrofitInstance.api.getMovie()
    }

    suspend fun getMovies() : Response<MovieList> {
        return RetrofitInstance.api.getMovies()
    }

    suspend fun getNew() : Response<MovieList> {
        return RetrofitInstance.api.getNew("2022", "year", API_KEY, 200, "8-10", "rating.kp")
    }
}