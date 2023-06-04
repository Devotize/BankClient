package com.sychev.shared.security

import java.math.BigInteger
import java.security.MessageDigest

actual class MD5 {

    private val mDigest = MessageDigest.getInstance("MD5")

    actual fun encrypt(data: Any?): String {
        mDigest.update(data.toString().toByteArray())
        return BigInteger(1, mDigest.digest()).toString(16)
    }

}