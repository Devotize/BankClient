package com.sychev.shared.data.remote.api

import com.sychev.shared.data.remote.client.client
import com.sychev.shared.data.remote.model.user_data.UsersDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val apiUrl = "https://hr.peterpartner.net/"

private val json by lazy {
    Json {
        ignoreUnknownKeys = true
    }
}

object BankUserServiceApi {

    private val httpClient = client

    suspend fun fetchUsers(): UsersDto {
        val responseData: String = httpClient.get(apiUrl + "test/android/v1/users.json").body()
        return json.decodeFromString(responseData)
    }

}