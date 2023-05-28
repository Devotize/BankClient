package com.sychev.shared.logger

class LoggerImpl : LoggerApi {

    override fun logXertz(message: Any?) {
        println("XERTZ: ${message}")
    }

    override fun log(tag: String, message: Any?) {
        println("$tag: $message")
    }
}

actual val logger: LoggerApi = LoggerImpl()