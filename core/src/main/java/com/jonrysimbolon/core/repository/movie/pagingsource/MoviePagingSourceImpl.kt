package com.jonrysimbolon.core.repository.movie.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jonrysimbolon.core.datasource.DataSource
import com.jonrysimbolon.core.model.MovieModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSourceImpl @Inject constructor(
    private val remote: DataSource,
    private val idCategory: String,
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = remote.getAllMovies(page, idCategory)
            val listData = responseData.first() // or last idk, let's see it/
            LoadResult.Page(
                data = listData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (listData.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}