package com.sychev.shared.backend.security

import io.ktor.util.encodeBase64
import io.ktor.utils.io.core.toByteArray

class JWT private constructor(
    private val value: String,
) {

    companion object {
        class Builder {
            private var claims: String? = null
            private var secretKey: String? = null

            private val defaultHeader = """{ "alg": "HS256", "typ": "JWT"}"""

            fun setClaims(claims: Map<String, String>): Builder {
                println("xertz: claims: ${claims}")
                this.claims = claims.toString()
                return this
            }

            fun setKey(key: String): Builder {
                this.secretKey = key
                return this
            }

            fun build(): JWT {
                if (secretKey == null) throw Error("Secret key should be provided")
                val unsignedToken = "${defaultHeader.toByteArray().encodeBase64()}.${
                    (claims ?: "{ }").toByteArray().encodeBase64()
                }"
                val signature = HMAC().encode(unsignedToken, secretKey!!)
                return JWT("${unsignedToken}.${signature}")
            }
        }

        fun getBuilder(): Builder = Builder()
    }

    override fun toString(): String =
        value

    override fun equals(other: Any?): Boolean {
        if (other as? JWT == null) return false
        return this.value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}