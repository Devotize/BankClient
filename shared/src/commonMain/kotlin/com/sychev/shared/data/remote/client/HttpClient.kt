package com.sychev.shared.data.remote.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal val client = HttpClient {
    install(ContentNegotiation) {
        json(
            json = Json {
                prettyPrint = true
                isLenient = true
            }
        )
    }
}