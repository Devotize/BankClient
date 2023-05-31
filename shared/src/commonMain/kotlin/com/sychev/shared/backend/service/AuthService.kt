package com.sychev.shared.backend.service

import com.sychev.shared.security.JWT

class AuthService private constructor() {

    private val authorisedPairs = hashMapOf<String, JWT>()  //email: token

    fun generateJWT(email: String): JWT {
        return JWT.getBuilder()
            .setKey(jwt_secret_key)
            .setClaims(mapOf(Pair("email", email)))
            .build().also {
                authorisedPairs[email] = it
            }
    }

    companion object {
        private val _instance by lazy { AuthService() }
        private val jwt_secret_key = "iKsS1puWka"
        fun getInstance() = _instance
    }

}