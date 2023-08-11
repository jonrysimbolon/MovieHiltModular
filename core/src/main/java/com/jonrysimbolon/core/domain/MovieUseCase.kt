package com.jonrysimbolon.core.domain

import androidx.paging.PagingData
import com.jonrysimbolon.core.model.CategoryModel
import com.jonrysimbolon.core.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getCategories(): Flow<List<CategoryModel>>
    suspend fun getMovies(id: String) : Flow<PagingData<MovieModel>>
}