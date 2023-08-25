package com.jonrysimbolon.core.domain.detail

import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface DetailMovieUseCase {
    suspend fun detailMovie(movieId: Int): Flow<ResultStatus<DetailMovieModel>>
}