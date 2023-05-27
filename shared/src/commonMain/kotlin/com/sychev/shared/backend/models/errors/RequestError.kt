package com.sychev.shared.backend.models.errors

enum class RequestError(val message: String, val code: Int) {

    BadCredentials(message = "Bad credentials", 401),
    UserAlreadyExists(message = "User already exists", code = 402),

}