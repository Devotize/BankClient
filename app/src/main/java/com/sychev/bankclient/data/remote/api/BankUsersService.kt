package com.sychev.bankclient.data.remote.api

import com.sychev.bankclient.data.remote.model.user_data.UsersDto
import retrofit2.http.GET

interface BankUsersService {
    @GET("test/android/v1/users.json")
    suspend fun getUsers(): UsersDto
}