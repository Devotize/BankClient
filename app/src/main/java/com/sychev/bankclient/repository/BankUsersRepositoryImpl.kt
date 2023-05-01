package com.sychev.bankclient.repository

import android.util.Log
import com.sychev.bankclient.data.local.dao.UserDao
import com.sychev.bankclient.data.local.mapper.UserEntityMapper
import com.sychev.bankclient.data.remote.api.BankUsersService
import com.sychev.bankclient.utils.TAG
import com.sychev.shared.domain.model.user_data.Users
import com.sychev.shared.remote.mapper.UsersDtoMapper

class BankUsersRepositoryImpl(
    private val bankUsersService: BankUsersService,
    private val userDao: UserDao,
    private val userEntityMapper: UserEntityMapper,
    private val usersDtoMapper: UsersDtoMapper,
) : BankUsersRepository {

    override suspend fun getUsers(): Users {
        Log.d(TAG, "getUsers: called")
        return usersDtoMapper.toDomainModel(bankUsersService.getUsers())
    }

    override suspend fun insertUsers(users: Users): Boolean {
        val userEntity = users.users.map {
            userEntityMapper.fromDomainModel(it)
        }
        val result = userDao.insertUsers(*userEntity.toTypedArray())
        return result.size == users.users.size
    }

    override suspend fun takeUsersFromCache(): Users {
        val result = userDao.getAllUsers()
        val users = Users(
            users = result.map{userEntityMapper.toDomainModel(it)}
        )
        return users
    }
}