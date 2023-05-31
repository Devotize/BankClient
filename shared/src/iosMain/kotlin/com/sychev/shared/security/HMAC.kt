package com.sychev.shared.security

actual class HMAC {

    actual fun encode(
        message: String,
        key: String,
        alg: String,
    ): String = "Encoding not implemented yet"

}