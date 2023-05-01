package com.sychev.shared.repository

import com.sychev.shared.data.remote.api.BankUserServiceApi
import com.sychev.shared.data.remote.mapper.UsersDtoMapper
import com.sychev.shared.domain.model.user_data.Users

class BankUsersRepositoryImpl(
    private val usersDtoMapper: UsersDtoMapper,
) : BankUsersRepository {

    private val api = BankUserServiceApi
    private var cachedUsers: Users? = null

    override suspend fun fetchUsers(): Users {
        return usersDtoMapper.toDomainModel(api.fetchUsers())
    }

    override suspend fun insertUsers(users: Users): Boolean {
        cachedUsers = users
        return true
    }

    override suspend fun takeUsersFromCache(): Users {
        return cachedUsers ?: Users(emptyList())
    }
}