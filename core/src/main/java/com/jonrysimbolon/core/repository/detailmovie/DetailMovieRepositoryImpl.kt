package com.jonrysimbolon.core.repository.detailmovie

import com.jonrysimbolon.core.datasource.DataSource
import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.utils.ResultStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailMovieRepositoryImpl @Inject constructor(
    private var remote: DataSource
) : DetailMovieRepository {
    override suspend fun getMovie(movieId: Int): Flow<ResultStatus<DetailMovieModel>> = flow {
        remote.getMovie(movieId)
    }
}