package com.sychev.shared.logger

import android.util.Log

class LoggerImpl : LoggerApi {

    override fun logXertz(message: Any?) {
        Log.d("XERTZ", message.toString())
    }

    override fun log(tag: String, message: Any?) {
        Log.d(tag, message.toString())
    }

}

actual val logger: LoggerApi = LoggerImpl()