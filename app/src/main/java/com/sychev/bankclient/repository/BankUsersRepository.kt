package com.sychev.bankclient.repository

import com.sychev.bankclient.domain.model.user_data.Users

interface BankUsersRepository {
    suspend fun getUsers(): Users

    suspend fun insertUsers(users: Users): Boolean
    suspend fun takeUsersFromCache(): Users
}