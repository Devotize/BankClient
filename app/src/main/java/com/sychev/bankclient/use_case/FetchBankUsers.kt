package com.sychev.bankclient.use_case

import com.sychev.bankclient.domain.data_state.DataState
import com.sychev.bankclient.domain.model.user_data.Users
import com.sychev.bankclient.repository.BankUsersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchBankUsers (
    private val bankUsersRepository: BankUsersRepository
) {
    fun execute(): Flow<DataState<Users>> = flow {
        try {
            emit(DataState.loading())
            val result = bankUsersRepository.getUsers()
            //just for real looking request
            delay(1500)
            emit(DataState.success(result))
        } catch (e: Exception) {
            emit(DataState.error("error: ${e.localizedMessage}"))
        }
    }
}