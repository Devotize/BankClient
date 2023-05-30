package com.sychev.shared.repository

import com.sychev.shared.backend.Backend
import com.sychev.shared.backend.models.base.RequestResult
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.domain.model.auth.LoginRequest
import com.sychev.shared.domain.model.auth.RegistrationRequest
import com.sychev.shared.domain.model.auth.Token
import com.sychev.shared.logger.logger

class AuthRepositoryImpl : AuthRepository {

    private val backend = Backend.getInstance()

    override suspend fun registerUser(registrationRequest: RegistrationRequest): RequestResult<Token> {
        val dataResult = backend.registerUser(
            registrationRequest.email,
            registrationRequest.password
        )
        logger.log(TAG, "registerUser request result: $dataResult")
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
        logger.log(TAG, "loginUser request result: $dataResult")
        return if (dataResult is ResultFail) {
            dataResult
        } else {
            ResultSuccess(Token(dataResult.getResult().data))
        }
    }

    companion object {
        private const val TAG = "AuthRepository"
    }

}