package com.sychev.shared.data.remote.model.currency


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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