package com.sychev.shared.remote.model.currency

import kotlinx.serialization.SerialName


data class ValuteDto(
    @SerialName("EUR")
    val eUR: CurrencyItemDto,
    @SerialName("GBP")
    val gBP: CurrencyItemDto,
    @SerialName("USD")
    val uSD: CurrencyItemDto,
)