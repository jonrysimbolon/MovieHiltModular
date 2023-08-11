package com.jonrysimbolon.moviehiltmodular.di

import com.jonrysimbolon.core.repository.category.CategoryRepository
import com.jonrysimbolon.core.repository.category.CategoryRepositoryImpl
import com.jonrysimbolon.core.repository.movie.MovieRepository
import com.jonrysimbolon.core.repository.movie.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Singleton
    @Binds
    abstract fun bindCategoryRepo(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Singleton
    @Binds
    abstract fun movieCategoryRepo(
        impl: MovieRepositoryImpl
    ): MovieRepository

}