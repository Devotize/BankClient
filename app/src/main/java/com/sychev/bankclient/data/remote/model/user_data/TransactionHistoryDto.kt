package com.sychev.bankclient.data.remote.model.user_data


import com.google.gson.annotations.SerializedName

data class TransactionHistoryDto(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("title")
    val title: String
)