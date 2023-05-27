package com.sychev.shared.backend

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

class BackendStorageManagerImpl private constructor() : BackendStorageManagerApi {

    private lateinit var _context: Context
    private val mutex = Mutex()

    fun providerAppContext(context: Context) {
        _context = context
    }

    override suspend fun writeToFile(fileName: String, content: String) {
        val file = getFile(fileName)
        withContext(Dispatchers.IO) {
            mutex.withLock {
                file.writeText(content)
            }
        }
    }

    override suspend fun readFromFile(fileName: String): String {
        val file = getFile(fileName)
        return withContext(Dispatchers.IO) {
            mutex.withLock {
                var data = ""
                file.forEachLine {
                    println("xertz: eachLine: $it")
                    data += it
                }
                data.also {
                    println("xertz: rawData: $it")
                }
            }
        }
    }

    override suspend fun clearFile(fileName: String) {
        val file = getFile(fileName)
        withContext(Dispatchers.IO) {
            mutex.withLock {
                file.writeText("")
            }
        }
    }

    private fun getFile(fileName: String): File {
        val directory = _context.getDir(directory_name, Context.MODE_PRIVATE)
        val file = File("${directory.absolutePath}/${fileName}")
        if (file.exists()) return file
        val isCreated = file.createNewFile()
        if (isCreated) {
            return file
        } else {
            throw IOException("File hasn't been created")
        }
    }

    companion object {
        private val _instance by lazy { BackendStorageManagerImpl() }
        private const val directory_name = "app_data"
        internal fun getInstance() = _instance
    }

}

actual fun provideBackendStorageManager(): BackendStorageManagerApi =
    BackendStorageManagerImpl.getInstance()

