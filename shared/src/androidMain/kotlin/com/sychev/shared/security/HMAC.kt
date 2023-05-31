package com.sychev.shared.security

import java.util.Formatter
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

actual class HMAC {

    actual fun encode(
        message: String,
        key: String,
        alg: String,
    ): String {
        val signKey = SecretKeySpec(key.toByteArray(), alg)
        val mac = Mac.getInstance(alg)
        mac.init(signKey)

        val bytes = mac.doFinal(message.toByteArray())
        return bytes.format()
    }

    private fun ByteArray.format(): String {
        val formatter = Formatter()
        this.forEach { formatter.format("%02x", it) }
        return formatter.toString()
    }

}