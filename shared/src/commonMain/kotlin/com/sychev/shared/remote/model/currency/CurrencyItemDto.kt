package com.sychev.shared.remote.model.currency


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyItemDto(
    @SerialName("CharCode")
    val charCode: String,
    @SerialName("ID")
    val iD: String,
    @SerialName("Name")
    val name: String,
    @SerialName("Nominal")
    val nominal: Int,
    @SerialName("NumCode")
    val numCode: String,
    @SerialName("Previous")
    val previous: Double,
    @SerialName("Value")
    val value: Double
)