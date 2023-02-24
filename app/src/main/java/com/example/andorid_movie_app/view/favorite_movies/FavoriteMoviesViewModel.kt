package com.example.andorid_movie_app.view.favorite_movies

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.andorid_movie_app.API_KEY
import com.example.andorid_movie_app.data.api.RetrofitInstance
import com.example.andorid_movie_app.data.db.FavoriteDatabase
import com.example.andorid_movie_app.model.MovieModel

class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    suspend fun getFavorites() : List<MovieModel> {
        val res = mutableListOf<MovieModel>()
        val data = FavoriteDatabase.getInstance(getApplication<Application>().applicationContext).getTaskDao().getAll()
        for (i in data) {
            val response = RetrofitInstance.api.getMovieById("id", i.id, API_KEY)
            res.add(response.body()!!)
        }
        return res
    }
}