package com.sychev.bankclient.di

import com.google.gson.GsonBuilder
import com.sychev.bankclient.data.remote.api.BankUsersService
import com.sychev.bankclient.data.remote.api.CurrencyService
import com.sychev.shared.remote.mapper.CurrencyDtoMapper
import com.sychev.shared.remote.mapper.UsersDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideBankUsersApi(
        client: OkHttpClient
    ): BankUsersService {
        return Retrofit.Builder()
            .baseUrl("https://hr.peterpartner.net/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(BankUsersService::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrencyApi(
        client: OkHttpClient
    ): CurrencyService {
        return Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CurrencyService::class.java)
    }

    @Singleton
    @Provides
    fun provideBankUsersDtoMapper() = UsersDtoMapper()

    @Singleton
    @Provides
    fun provideCurrencyDtoMapper() = CurrencyDtoMapper()
}