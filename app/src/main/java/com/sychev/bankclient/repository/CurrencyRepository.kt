package com.sychev.bankclient.repository

import com.sychev.shared.domain.model.currency.Currency

interface CurrencyRepository {
    suspend fun getCurrency(): Currency
}