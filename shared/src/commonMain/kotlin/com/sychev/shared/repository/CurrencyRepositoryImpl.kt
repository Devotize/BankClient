package com.sychev.shared.repository

import com.sychev.shared.data.remote.api.CurrencyServiceApi
import com.sychev.shared.data.remote.mapper.CurrencyDtoMapper
import com.sychev.shared.domain.model.currency.Currency

class CurrencyRepositoryImpl(
    private val currencyDtoMapper: CurrencyDtoMapper,
) : CurrencyRepository {

    private val api = CurrencyServiceApi

    override suspend fun getCurrency(): Currency {
        return currencyDtoMapper.toDomainModel(api.fetchCurrency())
    }
}