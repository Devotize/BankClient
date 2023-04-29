package com.sychev.bankclient.data.remote.api

import com.sychev.shared.remote.model.currency.CurrencyDto
import retrofit2.http.GET

interface CurrencyService {
    @GET("daily_json.js")
    suspend fun getCurrency(): CurrencyDto
}