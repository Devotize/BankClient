package com.sychev.shared.data.remote.api

import com.sychev.shared.data.remote.client.client
import com.sychev.shared.data.remote.model.currency.CurrencyDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val apiUrl = "https://www.cbr-xml-daily.ru/"

private val json by lazy {
    Json {
        ignoreUnknownKeys = true
    }
}

object CurrencyServiceApi {

    private val httpClient = client

    suspend fun fetchCurrency(): CurrencyDto {
        val responseData: String = httpClient.get(apiUrl + "daily_json.js").body()
        return json.decodeFromString(responseData)
    }

}