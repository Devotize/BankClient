package com.sychev.shared.data.remote.model.user_data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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