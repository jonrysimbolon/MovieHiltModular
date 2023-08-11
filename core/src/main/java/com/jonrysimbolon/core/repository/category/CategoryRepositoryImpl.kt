package com.jonrysimbolon.core.repository.category

import com.jonrysimbolon.core.datasource.DataSource
import com.jonrysimbolon.core.model.CategoryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val remote: DataSource,
): CategoryRepository {
    override suspend fun getAll(): Flow<List<CategoryModel>> =
        remote.getAllCategories()
}