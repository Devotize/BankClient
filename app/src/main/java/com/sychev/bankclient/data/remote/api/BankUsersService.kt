package com.sychev.bankclient.data.remote.api

import com.sychev.shared.remote.model.user_data.UsersDto
import retrofit2.http.GET

interface BankUsersService {
    @GET("test/android/v1/users.json")
    suspend fun getUsers(): UsersDto
}