package com.sychev.bankclient.domain.model.currency


data class Valute(
    val eUR: CurrencyItem,
    val gBP: CurrencyItem,
    val uSD: CurrencyItem,
    val rUB: CurrencyItem = CurrencyItem(
        charCode = "RUB",
        id = "",
        name = "Российский рубль",
        nominal = 1,
        numCode = "",
        previous = 1.0,
        value = 1.0,
    )
)