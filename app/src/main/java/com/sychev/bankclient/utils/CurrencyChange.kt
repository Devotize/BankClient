package com.sychev.bankclient.utils

object CurrencyChange {
    fun changeCurrency(
        usdAmount: Double,
        usdChangeRate: Double,
        targetChangeRate: Double): Double {
        return usdAmount * usdChangeRate / targetChangeRate
    }
}