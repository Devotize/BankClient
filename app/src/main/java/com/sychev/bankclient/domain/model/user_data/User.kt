package com.sychev.bankclient.domain.model.user_data

data class User(
    val balance: Double,
    val cardNumber: String,
    val cardholderName: String,
    val transactionHistory: List<TransactionHistory>,
    val type: String,
    val valid: String
)
