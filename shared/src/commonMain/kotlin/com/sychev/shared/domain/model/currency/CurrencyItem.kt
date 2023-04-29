package com.sychev.shared.domain.model.currency

data class CurrencyItem(
    val charCode: String,
    val id: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val previous: Double,
    val value: Double
)