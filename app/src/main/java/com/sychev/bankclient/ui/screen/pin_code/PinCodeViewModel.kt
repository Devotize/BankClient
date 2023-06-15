package com.sychev.bankclient.ui.screen.pin_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.bankclient.utils.DataStoreProvider
import com.sychev.bankclient.utils.getSavedPin
import com.sychev.bankclient.utils.getSavedToken
import com.sychev.bankclient.utils.savePin
import com.sychev.bankclient.utils.saveToken
import com.sychev.shared.backend.models.base.RequestResult
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    val token = DataStoreProvider.instance.getSavedToken()
        .stateIn(CoroutineScope(Dispatchers.IO), SharingStarted.Eagerly, "")
    val savedPin = DataStoreProvider.instance.getSavedPin()
        .stateIn(CoroutineScope(Dispatchers.IO), SharingStarted.Eagerly, "")
    private val _showError = MutableStateFlow(false)
    val showError = _showError.asStateFlow()


    fun getUserJWT(): String = runBlocking {
        DataStoreProvider.instance.getSavedToken().first()
    }

    fun getSavedPin() = runBlocking {
        DataStoreProvider.instance.getSavedPin().first()
    }

    fun onRepeatWrong() {
        showErrorForSecond()
    }

    fun onEnteredWrongPin() {
        showErrorForSecond()
    }

    fun saveNewPin(pin: String) {
        viewModelScope.launch {
            DataStoreProvider.instance.savePin(pin)
        }
    }

    suspend fun validateToken(): RequestResult<Unit> {
        return authRepository.validateToken(token.value).also {
            if (it is ResultFail) {
                DataStoreProvider.instance.saveToken("")
            }
        }
    }

    private fun showErrorForSecond() {
        viewModelScope.launch {
            _showError.value = true
            delay(1.seconds)
            _showError.value = false
        }
    }

}