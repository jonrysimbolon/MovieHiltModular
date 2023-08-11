package com.jonrysimbolon.moviehiltmodular.di

import com.jonrysimbolon.moviehiltmodular.presentation.adapter.MovieAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule{

    @Provides
    fun provideMovieAdapter(): MovieAdapter = MovieAdapter()

}