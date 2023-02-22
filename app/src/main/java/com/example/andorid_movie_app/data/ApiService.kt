package com.example.andorid_movie_app.data

import com.example.andorid_movie_app.model.MovieList
import com.example.andorid_movie_app.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie") // movie?search=2023&field=year&token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06
    suspend fun getNew(@Query("search")search: String, @Query("field")field: String,
                       @Query("token")apiKey: String, @Query("limit")limit: Int,
                       @Query("search")search2: String, @Query("field")field2: String) : Response<MovieList>
    @GET("movie")
    suspend fun getMovieById(@Query("field")field: String, @Query("search")id: Int, @Query("token")token: String) : Response<MovieModel>

    @GET("movie")
    suspend fun getMovies(@Query("search")name: String?, @Query("field")field1: String, @Query("isStrict")isStrict: String,
                          @Query("search")year: String?, @Query("field")field2: String,
                          @Query("search")rating: String?, @Query("field")field3: String,
                         @Query("token")token: String) : Response<MovieList>

    @GET("movie?limit=100")
    suspend fun getMovies(@Query("search")year: String?, @Query("field")field1: String,
                          @Query("search")rating: String?, @Query("field")field2: String,
                          @Query("token")token: String) : Response<MovieList>
}