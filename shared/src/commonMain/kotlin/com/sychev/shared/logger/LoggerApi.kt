package com.sychev.shared.logger

interface LoggerApi {

    fun logXertz(message: Any?)

    fun log(tag: String, message: Any?)

}

expect val logger: LoggerApi