package com.sychev.bankclient.data.remote.model.currency


import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valuteDto: ValuteDto
)