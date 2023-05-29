package com.sychev.shared.repository

import com.sychev.shared.domain.model.auth.LoginRequest
import com.sychev.shared.domain.model.auth.RegistrationRequest
import com.sychev.shared.domain.model.auth.Token

interface AuthRepository {

    suspend fun registerUser(registrationRequest: RegistrationRequest): Token

    suspend fun loginUser(loginRequest: LoginRequest): Token

}