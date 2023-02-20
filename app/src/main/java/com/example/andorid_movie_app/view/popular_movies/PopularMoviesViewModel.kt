package com.example.andorid_movie_app.view.popular_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.andorid_movie_app.data.MoviePageSource
import com.example.andorid_movie_app.data.Repository
import com.example.andorid_movie_app.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMoviesViewModel: ViewModel() {

     suspend fun getNewMovies() : List<MovieModel> {
        val repository = Repository()
        val movie = repository.getNew().body()!!
        return movie.movies.filter { it.poster != null && !it.name.isNullOrEmpty() }.toList()
    }
}