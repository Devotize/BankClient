package com.sychev.shared.data.remote.model.user_data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionHistoryDto(
    @SerialName("amount")
    val amount: String,
    @SerialName("date")
    val date: String,
    @SerialName("icon_url")
    val iconUrl: String,
    @SerialName("title")
    val title: String
)