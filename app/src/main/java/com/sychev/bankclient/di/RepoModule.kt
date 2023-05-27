package com.sychev.bankclient.di

import com.sychev.shared.data.remote.mapper.CurrencyDtoMapper
import com.sychev.shared.data.remote.mapper.UsersDtoMapper
import com.sychev.shared.repository.AuthRepository
import com.sychev.shared.repository.AuthRepositoryImpl
import com.sychev.shared.repository.BankUsersRepository
import com.sychev.shared.repository.BankUsersRepositoryImpl
import com.sychev.shared.repository.CurrencyRepository
import com.sychev.shared.repository.CurrencyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Singleton
    @Provides
    fun provideBankUsersRepo(
        usersDtoMapper: UsersDtoMapper,
    ): BankUsersRepository {
        return BankUsersRepositoryImpl(
            usersDtoMapper = usersDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        currencyDtoMapper: CurrencyDtoMapper,
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(
            currencyDtoMapper = currencyDtoMapper,
        )
    }

    @Singleton
    @Provides
    fun providerAuthRepository(): AuthRepository = AuthRepositoryImpl()
}