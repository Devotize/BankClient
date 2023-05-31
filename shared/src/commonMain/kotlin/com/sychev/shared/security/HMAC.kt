package com.sychev.shared.security

expect class HMAC() {

    fun encode(
        message: String,
        key: String,
        alg: String = "HmacSHA256"
    ): String

}