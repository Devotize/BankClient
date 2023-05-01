package com.sychev.bankclient.di

import com.sychev.shared.data.remote.mapper.CurrencyDtoMapper
import com.sychev.shared.data.remote.mapper.UsersDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Singleton
    @Provides
    fun provideBankUsersDtoMapper() = UsersDtoMapper()

    @Singleton
    @Provides
    fun provideCurrencyDtoMapper() = CurrencyDtoMapper()
}