package com.sychev.bankclient.di

import com.sychev.bankclient.repository.BankUsersRepository
import com.sychev.bankclient.repository.CurrencyRepository
import com.sychev.bankclient.use_case.FetchBankUsers
import com.sychev.bankclient.use_case.GetCurrency
import com.sychev.bankclient.use_case.GetUsersFromCache
import com.sychev.bankclient.use_case.InsertUsersToCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideFetchBankUsers(
        bankUsersRepository: BankUsersRepository,
    ): FetchBankUsers {
        return FetchBankUsers(bankUsersRepository = bankUsersRepository)
    }
    @Singleton
    @Provides
    fun provideGetCurrency(
        currencyRepository: CurrencyRepository
    ) = GetCurrency(currencyRepository = currencyRepository)

    @Singleton
    @Provides
    fun provideInsertUsersToCache(
        usersRepository: BankUsersRepository
    ) = InsertUsersToCache(usersRepository = usersRepository)

    @Singleton
    @Provides
    fun provideGetUsersFromCache(
        usersRepository: BankUsersRepository
    ) = GetUsersFromCache(usersRepository = usersRepository)
}