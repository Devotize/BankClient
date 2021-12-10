package com.sychev.bankclient.repository

import com.sychev.bankclient.domain.model.currency.Currency

interface CurrencyRepository {
    suspend fun getCurrency(): Currency
}