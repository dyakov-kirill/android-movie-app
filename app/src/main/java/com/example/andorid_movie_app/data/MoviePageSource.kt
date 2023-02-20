package com.example.andorid_movie_app.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.andorid_movie_app.model.MovieList
import com.example.andorid_movie_app.model.MovieModel
import retrofit2.HttpException

class MoviePageSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        if (query.isNotEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize

        val response = apiService.getMovies()
        if (response.isSuccessful) {
            val movies = checkNotNull(response.body()).movies
            val nextKey = if (movies.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(movies, prevKey, nextKey)
        } else {
            return LoadResult.Error(HttpException(response ))
        }
    }
}