package com.sychev.bankclient.use_case

import com.sychev.shared.domain.data_state.DataState
import com.sychev.shared.domain.model.user_data.Users
import com.sychev.shared.repository.BankUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertUsersToCache(
    private val usersRepository: BankUsersRepository
) {
    fun execute(users: Users): Flow<DataState<Boolean>> =  flow<DataState<Boolean>>{
        try {
            emit(DataState.loading())
            val result = usersRepository.insertUsers(users = users)
            emit(DataState.success(result))
        }catch (e: Exception) {
            emit(DataState.error("error: ${e.localizedMessage}"))
        }
    }
}