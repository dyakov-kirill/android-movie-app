package com.example.andorid_movie_app.data

import com.example.andorid_movie_app.model.MovieList
import com.example.andorid_movie_app.API_KEY
import com.example.andorid_movie_app.model.MovieModel
import okio.Utf8
import retrofit2.Response

class Repository {
    suspend fun getMovies(name: String, year: String, rating: String) : Response<MovieList> {
        return when (name.isEmpty()) {
            true -> RetrofitInstance.api.getMovies(
                year.ifEmpty { "0-3000" }, "year",
                rating.ifEmpty { "0-10" }, "rating.kp", API_KEY
            )
            false -> RetrofitInstance.api.getMovies(
                name, "name", "false",
                year.ifEmpty { "0-3000" }, "year",
                rating.ifEmpty { "0-10" }, "rating.kp", API_KEY
            )
        }
    }

    suspend fun getNew() : Response<MovieList> {
        return RetrofitInstance.api.getNew("2022", "year", API_KEY, 200, "8-10", "rating.kp")
    }
}