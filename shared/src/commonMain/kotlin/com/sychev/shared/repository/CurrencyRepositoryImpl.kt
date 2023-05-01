package com.sychev.shared.repository

import com.sychev.shared.domain.model.currency.Currency
import com.sychev.shared.remote.client.CurrencyRemoteApi
import com.sychev.shared.remote.mapper.CurrencyDtoMapper

class CurrencyRepositoryImpl(
    private val currencyDtoMapper: CurrencyDtoMapper,
) : CurrencyRepository {

    private val api = CurrencyRemoteApi

    override suspend fun getCurrency(): Currency {
        return currencyDtoMapper.toDomainModel(api.fetchCurrency())
    }
}