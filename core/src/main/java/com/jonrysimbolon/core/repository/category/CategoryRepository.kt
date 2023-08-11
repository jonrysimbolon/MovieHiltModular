package com.jonrysimbolon.core.repository.category

import com.jonrysimbolon.core.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getAll(): Flow<List<CategoryModel>>
}