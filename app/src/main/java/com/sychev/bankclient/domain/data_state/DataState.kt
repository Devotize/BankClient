package com.sychev.bankclient.domain.data_state

data class DataState<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val error: String? = null,
) {
    companion object {
        fun <T> loading(): DataState<T> = DataState(
            data = null,
            loading = true,
            error = null,
        )

        fun <T> success(data: T): DataState<T> = DataState(
            data = data,
            loading = false,
            error = null,
        )

        fun <T> error(message: String): DataState<T> = DataState(
            data = null,
            loading = false,
            error = message,
        )
    }
}