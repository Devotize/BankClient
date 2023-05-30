package com.sychev.shared.backend.models.errors

enum class RequestError(val message: String, val code: Int) {

    BadCredentials(message = "Bad credentials", 401),
    PassTooSmall(message = "Password is to small", 401),
    UserAlreadyExists(message = "User already exists", code = 402),
    UserNotExists(message = "No user associated with such e-mail", code = 403),
    WrongPassword(message = "Incorrect Password", code = 404),

}