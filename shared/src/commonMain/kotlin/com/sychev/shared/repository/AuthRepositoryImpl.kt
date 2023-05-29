package com.sychev.shared.repository

import com.sychev.shared.backend.Backend
import com.sychev.shared.backend.models.base.RequestResult
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.domain.model.auth.LoginRequest
import com.sychev.shared.domain.model.auth.RegistrationRequest
import com.sychev.shared.domain.model.auth.Token

class AuthRepositoryImpl : AuthRepository {

    private val backend = Backend.getInstance()

    override suspend fun registerUser(registrationRequest: RegistrationRequest): RequestResult<Token> {
        val dataResult = backend.registerUser(
            registrationRequest.email,
            registrationRequest.password
        )
        return if (dataResult is ResultFail) {
            dataResult
        } else {
            ResultSuccess(Token(dataResult.getResult().data))
        }
    }

    override suspend fun loginUser(loginRequest: LoginRequest): RequestResult<Token> {
        val dataResult = backend.registerUser(
            loginRequest.email,
            loginRequest.password
        )
        return if (dataResult is ResultFail) {
            dataResult
        } else {
            ResultSuccess(Token(dataResult.getResult().data))
        }
    }

}