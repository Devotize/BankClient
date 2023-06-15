package com.sychev.shared.backend.models.base

import com.sychev.shared.backend.models.errors.RequestErrorCodes

sealed class RequestResult<out T : Any> {

    fun getResultOrNull(): ResultSuccess<T>? = this as? ResultSuccess

    fun getResult(): ResultSuccess<T> =
        this as? ResultSuccess ?: error("cannot cast to ResultSuccess: $this")

}

data class ResultSuccess<out T : Any>(val data: T) : RequestResult<T>()

data class ResultFail(val error: RequestErrorCodes) : RequestResult<Nothing>()

