package com.sychev.shared.utils


object CurrencyChange {
    fun changeCurrencyAndRound(
        usdAmount: Double,
        usdChangeRate: Double,
        targetChangeRate: Double
    ): String {
        val changeResult = usdAmount * usdChangeRate / targetChangeRate
        return changeResult.roundTo(2).toMoneyString().replace(".", ",")
    }
}