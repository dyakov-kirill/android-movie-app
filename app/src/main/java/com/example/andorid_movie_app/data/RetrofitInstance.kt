package com.example.andorid_movie_app.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://api.kinopoisk.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}