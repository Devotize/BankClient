package com.sychev.bankclient.use_case

import com.sychev.bankclient.domain.data_state.DataState
import com.sychev.bankclient.domain.model.currency.Currency
import com.sychev.bankclient.repository.CurrencyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrency(
    private val currencyRepository: CurrencyRepository
) {
    fun execute(): Flow<DataState<Currency>> = flow {
        try {
            emit(DataState.loading())
            val result = currencyRepository.getCurrency()
            //just for real looking request
            delay(1500)
            emit(DataState.success(result))
        }catch (e: Exception) {
            emit(DataState.error("error: ${e.localizedMessage}"))
        }
    }
}