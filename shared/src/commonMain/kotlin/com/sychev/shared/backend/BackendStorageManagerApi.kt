package com.sychev.shared.backend


interface BackendStorageManagerApi {

    suspend fun writeToFile(fileName: String, content: String)

    suspend fun clearFile(fileName: String)

    suspend fun readFromFile(fileName: String): String

}

internal expect fun provideBackendStorageManager(): BackendStorageManagerApi

