package com.jonrysimbolon.core.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jonrysimbolon.core.datasource.DataSource
import com.jonrysimbolon.core.model.MovieModel
import com.jonrysimbolon.core.repository.movie.pagingsource.MoviePagingSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remote: DataSource,
) : MovieRepository {
    override suspend fun getAllPagingData(idCategory: String): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                MoviePagingSourceImpl(
                    remote,
                    idCategory
                )
            },
        ).flow
    }
}