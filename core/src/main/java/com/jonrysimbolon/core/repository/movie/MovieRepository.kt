package com.jonrysimbolon.core.repository.movie

import androidx.paging.PagingData
import com.jonrysimbolon.core.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getAllPagingData(idCategory: String): Flow<PagingData<MovieModel>>
}