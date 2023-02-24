package com.example.andorid_movie_app.view.popular_movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.andorid_movie_app.data.api.Repository
import com.example.andorid_movie_app.model.MovieModel

class PopularMoviesViewModel(application: Application) : AndroidViewModel(application) {
    var list: List<MovieModel> = listOf()
     suspend fun getNewMovies() : List<MovieModel> {
        val repository = Repository()
        val movie = repository.getNew()
         return if (movie.isSuccessful)
                    movie.body()!!.movies.filter { it.poster != null && !it.name.isNullOrEmpty() }.toList()
                else
                    listOf()
    }
}