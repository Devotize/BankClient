package com.sychev.bankclient.data.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserCardNumber(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_card_num")
        val USER_CARD_KEY = stringPreferencesKey("USER_CARD_NUMBER_KEY")
    }

    val getUserCardNumber: Flow<String?> = context.dataStore.data
        .map {
            it[USER_CARD_KEY]
        }

    suspend fun saveUserCardNumber(cardNumber: String) {
        context.dataStore.edit {
            it[USER_CARD_KEY] = cardNumber
        }
    }
}