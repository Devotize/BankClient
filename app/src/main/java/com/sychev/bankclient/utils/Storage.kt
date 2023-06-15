package com.sychev.bankclient.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_prefs")

fun DataStore<Preferences>.getSavedToken(): Flow<String> {
    return data.map { preferences ->
        preferences[TOKEN_PREFS_NAME] ?: ""
    }
}

suspend fun DataStore<Preferences>.saveToken(token: String) {
    edit { prefs ->
        prefs[TOKEN_PREFS_NAME] = token
    }
}

fun DataStore<Preferences>.getSavedPin(): Flow<String> {
    return data.map { preferences ->
        preferences[PIN_CODE_PREFS_NAME] ?: ""
    }
}

suspend fun DataStore<Preferences>.savePin(pin: String) {
    edit { prefs ->
        prefs[PIN_CODE_PREFS_NAME] = pin
    }
}

private val TOKEN_PREFS_NAME = stringPreferencesKey("jwt_prefs")
private val PIN_CODE_PREFS_NAME = stringPreferencesKey("pin_code_prefs")

object DataStoreProvider {

    private var _instance: DataStore<Preferences>? = null
    val instance get() = _instance!!

    fun initialize(context: Context) {
        _instance = context.dataStore
    }
}