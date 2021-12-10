package com.sychev.bankclient.repository

import com.sychev.bankclient.data.remote.api.CurrencyService
import com.sychev.bankclient.data.remote.mapper.CurrencyDtoMapper
import com.sychev.bankclient.domain.model.currency.Currency

class CurrencyRepository_Impl(
    private val currencyService: CurrencyService,
    private val currencyDtoMapper: CurrencyDtoMapper,
): CurrencyRepository {

    override suspend fun getCurrency(): Currency {
        return currencyDtoMapper.toDomainModel(currencyService.getCurrency())
    }
}