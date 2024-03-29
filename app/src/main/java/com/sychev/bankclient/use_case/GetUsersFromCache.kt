package com.sychev.bankclient.use_case

import android.util.Log
import com.sychev.bankclient.utils.TAG
import com.sychev.shared.domain.data_state.DataState
import com.sychev.shared.domain.model.user_data.Users
import com.sychev.shared.repository.BankUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetUsersFromCache(
    private val usersRepository: BankUsersRepository
) {
    fun execute(): Flow<DataState<Users>> = flow {
        emit(DataState.loading())
        val result = usersRepository.takeUsersFromCache()
        Log.d(TAG, "execute: result: $result")
        emit(DataState.success(result))
    }.catch {
        emit(DataState.error("error: ${it.localizedMessage}"))
    }
}