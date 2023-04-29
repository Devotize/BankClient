package com.sychev.shared.remote.model.user_data


import kotlinx.serialization.SerialName

data class UserDto(
    @SerialName("balance")
    val balance: Double,
    @SerialName("card_number")
    val cardNumber: String,
    @SerialName("cardholder_name")
    val cardholderName: String,
    @SerialName("transaction_history")
    val transactionHistoryDto: List<TransactionHistoryDto>,
    @SerialName("type")
    val type: String,
    @SerialName("valid")
    val valid: String
)