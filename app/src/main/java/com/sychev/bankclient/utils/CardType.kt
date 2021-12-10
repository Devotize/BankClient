package com.sychev.bankclient.utils

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import com.sychev.bankclient.R

object CardType {
    private enum class Type(val type: String) {
        MASTERCARD("mastercard"),
        VISA("visa"),
        UNIONPAY("unionpay")
    }
        fun getCardIconRes(type: String): Int {
            return when(type) {
                Type.MASTERCARD.type -> {
                    R.drawable.mastercard_icon
                }
                Type.VISA.type -> {
                    R.drawable.visa_icon
                }
                Type.UNIONPAY.type -> {
                    R.drawable.unionpay_icon
                }
                else -> {
                    R.drawable.credit_card_icon
                }
            }
        }

}
