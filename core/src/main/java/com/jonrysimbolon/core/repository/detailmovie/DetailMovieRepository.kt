package com.jonrysimbolon.core.repository.detailmovie

import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {
    suspend fun getMovie(movieId: Int): Flow<ResultStatus<DetailMovieModel>>
}