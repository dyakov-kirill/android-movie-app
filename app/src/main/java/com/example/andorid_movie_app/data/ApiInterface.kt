package com.example.andorid_movie_app.data

import com.example.andorid_movie_app.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie?search=%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3%20%D0%B8%D0%B7%20%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0&field=name&isStrict=false&token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06")
    suspend fun getMovie() : Response<MovieList>

    @GET("movie?field=rating.kp&search=7-10&field=year&search=2017-2020&field=typeNumber&search=2&sortField=year&sortType=1&sortField=votes.imdb&sortType=-1&token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06")
    suspend fun getMovies() : Response<MovieList>

    @GET("movie") // movie?search=2023&field=year&token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06
    suspend fun getNew(@Query("search")search: String, @Query("field")field: String, @Query("token")apiKey: String) : Response<MovieList>
}