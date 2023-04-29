package com.sychev.shared.remote.model.currency


import kotlinx.serialization.SerialName

data class CurrencyDto(
    @SerialName("Date")
    val date: String,
    @SerialName("PreviousDate")
    val previousDate: String,
    @SerialName("PreviousURL")
    val previousURL: String,
    @SerialName("Timestamp")
    val timestamp: String,
    @SerialName("Valute")
    val valuteDto: ValuteDto
)