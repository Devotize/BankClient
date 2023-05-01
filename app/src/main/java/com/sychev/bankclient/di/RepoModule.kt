package com.sychev.bankclient.di

import com.sychev.bankclient.data.local.dao.UserDao
import com.sychev.bankclient.data.local.mapper.UserEntityMapper
import com.sychev.bankclient.data.remote.api.BankUsersService
import com.sychev.bankclient.repository.BankUsersRepository
import com.sychev.bankclient.repository.BankUsersRepositoryImpl
import com.sychev.shared.remote.mapper.CurrencyDtoMapper
import com.sychev.shared.remote.mapper.UsersDtoMapper
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
        bankUsersService: BankUsersService,
        userDao: UserDao,
        userEntityMapper: UserEntityMapper,
        usersDtoMapper: UsersDtoMapper,
    ): BankUsersRepository {
        return BankUsersRepositoryImpl(
            bankUsersService = bankUsersService,
            userDao = userDao,
            userEntityMapper = userEntityMapper,
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
}