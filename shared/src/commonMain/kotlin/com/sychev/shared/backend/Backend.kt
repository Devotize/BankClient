package com.sychev.shared.backend

import com.sychev.shared.backend.models.AuthDataModel
import com.sychev.shared.backend.models.UserPropertiesDataModel
import com.sychev.shared.backend.models.base.RequestResult
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.backend.models.errors.RequestError
import com.sychev.shared.logger.logger
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class Backend private constructor() {

    private val storageManager: BackendStorageManagerApi = provideBackendStorageManager()

    suspend fun registerUser(
        email: String,
        password: String,
    ): RequestResult<String> {
        if (email.isEmpty() || password.isEmpty()) {
            return ResultFail(RequestError.BadCredentials)
        }
        val allCredsData = getAuthCredentials()
        if (allCredsData.properties.find { it.userEmail == email } != null) {
            return ResultFail(RequestError.UserAlreadyExists)
        }
        val mutableData = allCredsData.properties.toMutableList()
        mutableData.add(UserPropertiesDataModel(email, password))
        val newDataModel = AuthDataModel(
            mutableData
        )
        saveData(auth_creds_filename, newDataModel)
        return ResultSuccess("${getAuthCredentials()}")
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
        fun getInstance() = _instance
        private const val auth_creds_filename = "auth_creds.txt"
        private const val TAG = "APP_LOCAL_BACKEND"
    }

}