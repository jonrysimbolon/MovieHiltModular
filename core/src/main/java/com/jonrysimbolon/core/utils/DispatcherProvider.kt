package com.jonrysimbolon.core.utils

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class MovieDispatcher(val movieDispatcher: MovieDispatchers)

enum class MovieDispatchers{
    MAIN,
    IO,
    DEFAULT
}

/*

@Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
@Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

====================================================================

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopesModule {
    @Provides
    @Singleton
    @ApplicationScope
    fun providesCoroutineScope(
        @Dispatcher(Default) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}

 */

/*
interface DispatcherProvider {

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher

}

class DefaultDispatcherProvider : DispatcherProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

}*/
