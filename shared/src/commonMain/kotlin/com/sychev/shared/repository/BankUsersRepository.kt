package com.sychev.shared.repository

import com.sychev.shared.domain.model.user_data.Users

interface BankUsersRepository {
    suspend fun fetchUsers(): Users

    suspend fun insertUsers(users: Users): Boolean
    suspend fun takeUsersFromCache(): Users
}