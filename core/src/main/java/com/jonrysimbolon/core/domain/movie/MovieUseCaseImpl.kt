package com.jonrysimbolon.core.domain.movie

import androidx.paging.PagingData
import com.jonrysimbolon.core.model.CategoryModel
import com.jonrysimbolon.core.model.MovieModel
import com.jonrysimbolon.core.repository.category.CategoryRepository
import com.jonrysimbolon.core.repository.movie.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override suspend fun getCategories(): Flow<List<CategoryModel>> =
        categoryRepository.getAll()

    override suspend fun getMovies(id: String): Flow<PagingData<MovieModel>> =
        movieRepository.getAllPagingData(id)

}