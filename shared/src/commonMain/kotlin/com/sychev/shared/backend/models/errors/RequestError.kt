package com.sychev.shared.backend.models.errors

enum class RequestError(val message: String, val code: Int) {

    BadCredentials(message = "Bad credentials", 401),
    PassTooSmall(message = "Password to small", 401),
    UserAlreadyExists(message = "User already exists", code = 402),
    UserNotExists(message = "User not exists", code = 403),
    WrongPassword(message = "Incorrect Password", code = 403),

}