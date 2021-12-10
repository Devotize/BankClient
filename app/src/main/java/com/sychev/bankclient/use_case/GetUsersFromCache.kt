package com.sychev.bankclient.use_case

import android.util.Log
import com.sychev.bankclient.domain.data_state.DataState
import com.sychev.bankclient.domain.model.user_data.Users
import com.sychev.bankclient.repository.BankUsersRepository
import com.sychev.bankclient.utils.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsersFromCache(
    private val usersRepository: BankUsersRepository
) {
    fun execute(): Flow<DataState<Users>> = flow {
        try {
            emit(DataState.loading())
            val result = usersRepository.takeUsersFromCache()
            Log.d(TAG, "execute: result: $result")
            emit(DataState.success(result))
        }catch (e: Exception) {
            emit(DataState.error("error: ${e.localizedMessage}"))
        }
    }
}