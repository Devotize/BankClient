package com.sychev.shared.domain.model.auth

data class RegistrationRequest(
    val email: String,
    val password: String,
)