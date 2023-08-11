package com.jonrysimbolon.moviehiltmodular.di

import com.jonrysimbolon.core.utils.MovieDispatcher
import com.jonrysimbolon.core.utils.MovieDispatchers
import com.jonrysimbolon.core.utils.MovieDispatchers.DEFAULT
import com.jonrysimbolon.core.utils.MovieDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/*@InstallIn(SingletonComponent::class)
@Module
abstract class DispatcherModule {

    @Singleton
    @Binds
    abstract fun provideDispatcherProvider(
        impl: DefaultDispatcherProvider
    ): DispatcherProvider

}*/

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

    @Provides
    @MovieDispatcher(IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MovieDispatcher(DEFAULT)
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @MovieDispatcher(MovieDispatchers.MAIN)
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}