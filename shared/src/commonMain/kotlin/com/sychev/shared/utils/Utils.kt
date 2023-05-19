package com.sychev.shared.utils

fun Double.toMoneyString(): String {
    val partBeforeFloatingPoint = this.toString().substringBefore(".")
    var partAfterFloatingPoint = this.toString().substringAfter(".")
    val sb = StringBuilder(partBeforeFloatingPoint.reversed())
    val interval = 3
    for (i in 0 until partBeforeFloatingPoint.length / interval) {
        if (((i + 1) * interval) + i != partBeforeFloatingPoint.length + 1) {
            sb.insert(((i + 1) * interval) + i, " ")
        }
    }
    if (partAfterFloatingPoint.length < 2) {
        for (i in partAfterFloatingPoint.length until 2) {
            partAfterFloatingPoint += "0"
        }
    }
    return sb.toString().reversed().plus(".${partAfterFloatingPoint}")
}

fun Double.roundTo(decimals: Int): Double {
    val str = this.toString()
    val partBeforeFloatingPoint = str.substringBefore(".")
    val partAfterFloatingPoint = str.substringAfter(".")
    var result = "$partBeforeFloatingPoint."
    for (i in 0 until decimals) {
        val nextDigit = partAfterFloatingPoint.getOrNull(i) ?: "0"
        result += nextDigit
    }
    return result.toDouble()
}