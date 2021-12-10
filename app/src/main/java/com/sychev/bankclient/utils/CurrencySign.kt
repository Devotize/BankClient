package com.sychev.bankclient.utils

object CurrencySign {
    fun getCurrencySign(
        charCode: String,
    ): String {
        return when (charCode) {
            CurrencyCharCode.USD.code -> "$"
            CurrencyCharCode.GBP.code -> "£"
            CurrencyCharCode.RUB.code -> "₽"
            CurrencyCharCode.EUR.code -> "€"
            else -> "TBD"
        }
    }

    private enum class CurrencyCharCode(val code: String) {
        USD("USD"),
        RUB("RUB"),
        EUR("EUR"),
        GBP("GBP"),
    }
}