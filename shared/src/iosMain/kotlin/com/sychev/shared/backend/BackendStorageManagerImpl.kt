package com.sychev.shared.backend

class BackendStorageManagerImpl private constructor() : BackendStorageManagerApi {

    override suspend fun writeToFile(fileName: String, content: String) {
        //TODO("Not yet implemented")
    }

    override suspend fun clearFile(fileName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readFromFile(fileName: String): String {
        return "" //TODO("Not yet implemented")
    }

    companion object {
        private val _instance by lazy { BackendStorageManagerImpl() }
        internal fun getInstance() = _instance
    }
}

actual fun provideBackendStorageManager(): BackendStorageManagerApi =
    BackendStorageManagerImpl.getInstance()