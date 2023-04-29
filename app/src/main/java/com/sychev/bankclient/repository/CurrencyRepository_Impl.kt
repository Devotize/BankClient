package com.sychev.bankclient.repository

import com.sychev.bankclient.data.remote.api.CurrencyService
import com.sychev.shared.domain.model.currency.Currency
import com.sychev.shared.remote.mapper.CurrencyDtoMapper

class CurrencyRepository_Impl(
    private val currencyService: CurrencyService,
    private val currencyDtoMapper: CurrencyDtoMapper,
): CurrencyRepository {

    override suspend fun getCurrency(): Currency {
        return currencyDtoMapper.toDomainModel(currencyService.getCurrency())
    }
}