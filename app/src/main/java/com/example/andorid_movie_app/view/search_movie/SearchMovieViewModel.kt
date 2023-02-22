package com.example.andorid_movie_app.view.search_movie

import androidx.lifecycle.ViewModel
import com.example.andorid_movie_app.data.Repository
import com.example.andorid_movie_app.model.MovieList
import com.example.andorid_movie_app.model.MovieModel
import retrofit2.Response

class SearchMovieViewModel : ViewModel() {

    suspend fun searchMovie(name: String, year: String, rating: String) : List<MovieModel> {
        val repository = Repository()
        return repository.getMovies(name, year, rating).body()!!.movies.filter { it.poster != null && !it.name.isNullOrEmpty() }.toList()
    }
}