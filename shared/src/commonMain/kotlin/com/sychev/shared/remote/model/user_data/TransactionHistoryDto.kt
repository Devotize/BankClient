package com.sychev.shared.remote.model.user_data


import kotlinx.serialization.SerialName

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