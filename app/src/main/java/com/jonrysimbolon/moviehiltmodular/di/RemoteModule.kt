package com.jonrysimbolon.moviehiltmodular.di

import com.jonrysimbolon.core.BuildConfig
import com.jonrysimbolon.core.datasource.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val AUTHORIZATION = "Authorization"

private val loggingInterceptor = with(HttpLoggingInterceptor()) {
    if (BuildConfig.DEBUG)
        setLevel(HttpLoggingInterceptor.Level.BODY)
    else
        setLevel(HttpLoggingInterceptor.Level.NONE)
}

@InstallIn(SingletonComponent::class)
@Module
object RemoteModule {

    @Provides
    @Singleton
    fun provideClient() = with(OkHttpClient.Builder()) {
        addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader(
                AUTHORIZATION, BuildConfig.Authorization
            ).build()
            chain.proceed(request)
        }
        addInterceptor(loggingInterceptor)
        build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = with(Retrofit.Builder()) {
        baseUrl(BuildConfig.Base_Url)
        addConverterFactory(GsonConverterFactory.create())
        client(provideClient())
        build()
    }
}