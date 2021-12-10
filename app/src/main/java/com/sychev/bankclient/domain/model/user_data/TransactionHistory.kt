package com.sychev.bankclient.domain.model.user_data

data class TransactionHistory(
    val amount: String,
    val date: String,
    val iconUrl: String,
    val title: String
)
