package com.sychev.bankclient.data.remote.model.currency


import com.google.gson.annotations.SerializedName

data class ValuteDto(
    @SerializedName("EUR")
    val eUR: CurrencyItemDto,
    @SerializedName("GBP")
    val gBP: CurrencyItemDto,
    @SerializedName("USD")
    val uSD: CurrencyItemDto,
)