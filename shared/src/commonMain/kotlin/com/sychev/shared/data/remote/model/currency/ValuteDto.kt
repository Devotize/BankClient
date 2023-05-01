package com.sychev.shared.data.remote.model.currency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValuteDto(
    @SerialName("EUR")
    val eUR: CurrencyItemDto,
    @SerialName("GBP")
    val gBP: CurrencyItemDto,
    @SerialName("USD")
    val uSD: CurrencyItemDto,
)