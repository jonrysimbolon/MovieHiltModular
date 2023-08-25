package com.jonrysimbolon.moviehiltmodular.di

import com.jonrysimbolon.core.domain.detail.DetailMovieUseCase
import com.jonrysimbolon.core.domain.detail.DetailMovieUseCaseImpl
import com.jonrysimbolon.core.domain.movie.MovieUseCase
import com.jonrysimbolon.core.domain.movie.MovieUseCaseImpl
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

    @Singleton
    @Binds
    abstract fun bindDetailMovieUseCase(
        impl: DetailMovieUseCaseImpl
    ): DetailMovieUseCase
}