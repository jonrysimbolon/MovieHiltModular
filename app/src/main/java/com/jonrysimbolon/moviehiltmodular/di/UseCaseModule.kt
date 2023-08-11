package com.jonrysimbolon.moviehiltmodular.di

import com.jonrysimbolon.core.domain.MovieUseCase
import com.jonrysimbolon.core.domain.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindMovieUseCase(
        impl: MovieUseCaseImpl
    ): MovieUseCase
}