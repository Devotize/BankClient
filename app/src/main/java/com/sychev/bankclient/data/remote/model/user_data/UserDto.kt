package com.sychev.bankclient.data.remote.model.user_data


import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("card_number")
    val cardNumber: String,
    @SerializedName("cardholder_name")
    val cardholderName: String,
    @SerializedName("transaction_history")
    val transactionHistoryDto: List<TransactionHistoryDto>,
    @SerializedName("type")
    val type: String,
    @SerializedName("valid")
    val valid: String
)