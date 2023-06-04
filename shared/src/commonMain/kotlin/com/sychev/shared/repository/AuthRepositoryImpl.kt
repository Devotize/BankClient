package com.sychev.shared.repository

import com.sychev.shared.backend.Backend
import com.sychev.shared.backend.models.base.RequestResult
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.domain.model.auth.LoginRequest
import com.sychev.shared.domain.model.auth.RegistrationRequest
import com.sychev.shared.domain.model.auth.Token
import com.sychev.shared.logger.logger
import com.sychev.shared.security.MD5

class AuthRepositoryImpl : AuthRepository {

    private val backend = Backend.getInstance()
    private val md5 = MD5()

    override suspend fun registerUser(registrationRequest: RegistrationRequest): RequestResult<Token> {
        val encryptedEmail = md5.encrypt(registrationRequest.email)
        val encryptedPass = md5.encrypt(registrationRequest.password)
        val dataResult = backend.registerUser(
            encryptedEmail,
            encryptedPass
        )
        logger.log(TAG, "registerUser request token: $dataResult")
        return if (dataResult is ResultFail) {
            dataResult
        } else {
            ResultSuccess(Token(dataResult.getResult().data))
        }
    }

    override suspend fun loginUser(loginRequest: LoginRequest): RequestResult<Token> {
        val encryptedEmail = md5.encrypt(loginRequest.email)
        val encryptedPass = md5.encrypt(loginRequest.password)
        val dataResult = backend.loginUser(
            encryptedEmail,
            encryptedPass
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