package com.jonrysimbolon.moviehiltmodular.di

import com.jonrysimbolon.core.datasource.DataSource
import com.jonrysimbolon.core.datasource.DataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class MovieModule {
    @Singleton
    @Binds
    abstract fun bindRemoteMovie(
        impl: DataSourceImpl
    ): DataSource
}