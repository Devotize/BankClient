package com.sychev.bankclient.ui.screen.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.bankclient.data.local.data_store.StoreUserCardNumber
import com.sychev.bankclient.domain.model.currency.Currency
import com.sychev.bankclient.domain.model.currency.CurrencyItem
import com.sychev.bankclient.domain.model.user_data.User
import com.sychev.bankclient.domain.model.user_data.Users
import com.sychev.bankclient.use_case.FetchBankUsers
import com.sychev.bankclient.use_case.GetCurrency
import com.sychev.bankclient.use_case.GetUsersFromCache
import com.sychev.bankclient.use_case.InsertUsersToCache
import com.sychev.bankclient.utils.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchBankUsers: FetchBankUsers,
    private val getCurrency: GetCurrency,
    private val insertUsersToCache: InsertUsersToCache,
    private val getUsersFromCache: GetUsersFromCache,
): ViewModel() {

    val users = mutableStateOf<Users?>(null)
    val currency = mutableStateOf<Currency?>(null)
    val loadingUsers = mutableStateOf(false)
    val loadingCurrency = mutableStateOf(false)
    val currentUser = mutableStateOf<User?>(null)
    val selectedCurrency = mutableStateOf<CurrencyItem?>(null)
    val previouslySelectedUserCardNumber = mutableStateOf<String?>(null)

    private fun fetchBankUsers() {
        Log.d(TAG, "fetchBankUsers: called")
        fetchBankUsers.execute().onEach { dataState ->
            loadingUsers.value = dataState.loading
            dataState.data?.let {
                users.value = it
                currentUser.value = it.users[0]
                it.users.forEach { user ->
                    if (user.cardNumber == previouslySelectedUserCardNumber.value) {
                        currentUser.value = user
                    }
                }
                insertUsersToCache(it)
                Log.d(TAG, "fetchBankUsers: ${it}")
            }
            dataState.error?.let {
                Log.d(TAG, "fetchBankUsers: $it")
                getUsersFromCache()
            }
        }.launchIn(viewModelScope)
    }
    
    private fun getCurrency() {
        getCurrency.execute().onEach { dataState ->
            loadingCurrency.value = dataState.loading
            dataState.data?.let {
                selectedCurrency.value = it.valute.gBP
                currency.value = it
                Log.d(TAG, "getCurrency: ${it}")
            }
            dataState.error?.let {
                Log.d(TAG, "getCurrency: $it")
            }
        }.launchIn(viewModelScope)
    }
    
    private fun insertUsersToCache(users: Users) {
        Log.d(TAG, "insertUsersToCache: called")
        insertUsersToCache.execute(users).onEach { dataState ->  
            dataState.data?.let {
                Log.d(TAG, "insertUsersToCache: users inserted")
            }
        }.launchIn(viewModelScope)
    }

    private fun getUsersFromCache() {
        getUsersFromCache.execute().onEach { dataState ->
            loadingUsers.value = dataState.loading
            dataState.data?.let {
                users.value = it
                currentUser.value = it.users[0]
                it.users.forEach { user ->
                    if (user.cardNumber == previouslySelectedUserCardNumber.value) {
                        currentUser.value = user
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onSelectedCurrencyChange(newCurrencyItem: CurrencyItem) {
        selectedCurrency.value = newCurrencyItem
    }

    fun onCurrentUserChange(newUser: User) {
        currentUser.value = newUser
    }

    fun setPreviouslySelectedUserCardNumber(cardNumber: String) {
        previouslySelectedUserCardNumber.value = cardNumber
    }

    fun refreshData() {
        users.value = null
        currency.value = null
        currentUser.value = null
        fetchBankUsers()
        getCurrency()
    }

}