package com.sychev.shared.repository

import com.sychev.shared.backend.models.base.RequestResult
import com.sychev.shared.domain.model.auth.LoginRequest
import com.sychev.shared.domain.model.auth.RegistrationRequest
import com.sychev.shared.domain.model.auth.Token

interface AuthRepository {

    suspend fun registerUser(registrationRequest: RegistrationRequest): RequestResult<Token>

    suspend fun loginUser(loginRequest: LoginRequest): RequestResult<Token>

    suspend fun validateToken(token: String): RequestResult<Unit>

}