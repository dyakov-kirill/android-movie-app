package com.example.andorid_movie_app.view.search_movie

import androidx.lifecycle.ViewModel
import com.example.andorid_movie_app.data.api.Repository
import com.example.andorid_movie_app.model.MovieModel

class SearchMovieViewModel : ViewModel() {

    var list: List<MovieModel> = listOf()
    suspend fun searchMovie(name: String, year: String, rating: String) : List<MovieModel> {
        val repository = Repository()
        val movie = repository.getMovies(name, year, rating)
        return if (movie.isSuccessful)
            movie.body()!!.movies.filter { it.poster != null && !it.name.isNullOrEmpty() }.toList()
        else
            listOf()
    }
}