package com.sychev.shared.backend

import com.sychev.shared.backend.models.AuthDataModel
import com.sychev.shared.backend.models.TokenDataModel
import com.sychev.shared.backend.models.UserPropertiesDataModel
import com.sychev.shared.backend.models.base.RequestResult
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.backend.models.errors.RequestErrorCodes
import com.sychev.shared.backend.service.AuthService
import com.sychev.shared.logger.logger
import com.sychev.shared.utils.getCurrentSystemTimeSeconds
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class Backend private constructor() {

    private val storageManager: BackendStorageManagerApi = provideBackendStorageManager()
    private val authService by lazy { AuthService.getInstance() }

    suspend fun registerUser(
        email: String,
        password: String,
    ): RequestResult<Pair<String, Long>> {
        if (email.isEmpty() || password.isEmpty()) {
            return ResultFail(RequestErrorCodes.BadCredentials)
        }
        if (password.length < MIN_PASS_LENGTH) {
            return ResultFail(RequestErrorCodes.PassTooSmall)
        }
        val allCredsData = getAuthCredentials()
        if (allCredsData.properties.find { it.userEmail == email } != null) {
            return ResultFail(RequestErrorCodes.UserAlreadyExists)
        }
        val mutableData = allCredsData.properties.toMutableList()
        mutableData.add(UserPropertiesDataModel(email, password))
        val newDataModel = AuthDataModel(
            mutableData
        )
        saveData(auth_creds_filename, newDataModel)
        val tokenPair = createJWTWithExpTime(email)
        return ResultSuccess(tokenPair)
    }

    suspend fun loginUser(
        email: String,
        password: String,
    ): RequestResult<Pair<String, Long>> {
        if (email.isEmpty() || password.isEmpty()) {
            return ResultFail(RequestErrorCodes.BadCredentials)
        }
        val allCredsData = getAuthCredentials()
        val userCreds = allCredsData.properties.find { it.userEmail == email }
            ?: return ResultFail(RequestErrorCodes.UserNotExists)
        if (userCreds.userPass != password) {
            return ResultFail(RequestErrorCodes.WrongPassword)
        }
        val tokenPair = createJWTWithExpTime(email)
        return ResultSuccess(tokenPair)
    }

    suspend fun verifyJWT(
        jwt: String,
    ): RequestResult<Unit> {
        val allTokens = getAllJWTs()
        logger.log(TAG, "allJwts: ${allTokens}")
        logger.log(TAG, "privededToken: ${jwt}")
        return if (allTokens.isJWTValid(jwt)) {
            ResultSuccess(Unit)
        } else {
            try {
                val mutableAllTokens = allTokens.toMutableList()
                mutableAllTokens.removeAll { it.first == jwt }
                saveData(tokens_storage_filename, TokenDataModel(mutableAllTokens))
            } catch (e: Exception) {
                logger.log(TAG, "something went wrong with removing token from storage")
            }
            ResultFail(RequestErrorCodes.JWTError)
        }
    }

    private fun List<Pair<String, Long>>.isJWTValid(jwt: String): Boolean {
        val ourSideJWT = this.firstOrNull { it.first == jwt } ?: return false
        return ourSideJWT.second > getCurrentSystemTimeSeconds()
    }

    private suspend fun getAuthCredentials(): AuthDataModel {
        val rawData = storageManager.readFromFile(auth_creds_filename)
        val jsonData: AuthDataModel? = try {
            Json.decodeFromString<AuthDataModel>(rawData)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        return if (jsonData == null) {
            val dataModel = AuthDataModel.emptyModel
            saveData(auth_creds_filename, dataModel)
            dataModel
        } else {
            jsonData
        }
    }

    private suspend fun createJWTWithExpTime(email: String): Pair<String, Long> {
        val jwt = authService.generateJWT(email)
        val currTimeSeconds = getCurrentSystemTimeSeconds()
        val expTime = currTimeSeconds + (ONE_HOURS_TIMESTAMP * 2)
        return Pair(jwt.toString(), expTime).also {
            saveData(tokens_storage_filename, TokenDataModel(listOf(it)))
        }
    }

    private suspend fun getAllJWTs(): List<Pair<String, Long>> {
        val rawData = storageManager.readFromFile(tokens_storage_filename)
        val jsonData: TokenDataModel? = try {
            Json.decodeFromString<TokenDataModel>(rawData)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        return if (jsonData == null) {
            val dataModel = TokenDataModel.emptyModel
            saveData(tokens_storage_filename, dataModel)
            dataModel.properties
        } else {
            jsonData.properties
        }
    }

    private suspend inline fun <reified T> saveData(fileName: String, data: T) {
        val stringData = Json.encodeToString(data)
        logger.log(TAG, "saving data to local storage, fileName: $fileName, data: $stringData")
        storageManager.writeToFile(fileName, stringData)
    }

    private suspend fun clearFile(fileName: String) {
        storageManager.clearFile(fileName)
    }

    companion object {
        private val _instance by lazy { Backend() }
        private val MIN_PASS_LENGTH = 6
        fun getInstance() = _instance
        private const val auth_creds_filename = "auth_creds.txt"
        private const val tokens_storage_filename = "tokens_storage.txt"
        private const val TAG = "APP_LOCAL_BACKEND"
        private const val ONE_HOURS_TIMESTAMP = 3600
    }

}