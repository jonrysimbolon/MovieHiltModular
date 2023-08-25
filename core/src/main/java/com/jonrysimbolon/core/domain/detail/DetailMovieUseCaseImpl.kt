package com.jonrysimbolon.core.domain.detail

import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.repository.detailmovie.DetailMovieRepository
import com.jonrysimbolon.core.utils.ResultStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailMovieUseCaseImpl @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository
) : DetailMovieUseCase {
    override suspend fun detailMovie(movieId: Int): Flow<ResultStatus<DetailMovieModel>> =
        detailMovieRepository.getMovie(movieId)

}