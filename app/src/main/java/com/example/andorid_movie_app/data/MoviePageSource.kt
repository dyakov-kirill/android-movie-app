package com.example.andorid_movie_app.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.andorid_movie_app.data.api.ApiService
import com.example.andorid_movie_app.data.api.Repository
import com.example.andorid_movie_app.model.MovieModel

class MoviePageSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val repository = Repository()
            val currentPage = params.key ?: 1
            val response = repository.getPopularMovies(currentPage)
            val data = response.body()!!.movies.filter { it.poster != null && !it.name.isNullOrEmpty() }.toList()
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}