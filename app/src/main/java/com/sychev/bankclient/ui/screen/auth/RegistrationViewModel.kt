package com.sychev.bankclient.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sychev.bankclient.utils.DataStoreProvider
import com.sychev.bankclient.utils.saveToken
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.backend.models.errors.RequestErrorCodes
import com.sychev.shared.domain.model.auth.RegistrationRequest
import com.sychev.shared.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerSuccessEventStream = MutableSharedFlow<Unit>()
    val registerSuccessEventStream = _registerSuccessEventStream.asSharedFlow()

    private val _errorStream = MutableSharedFlow<RequestErrorCodes>()
    val errorStream = _errorStream.asSharedFlow()

    fun registerUser(email: String, password: String) {
        CoroutineScope(Dispatchers.Default).launch {
            authRepository.registerUser(
                RegistrationRequest(
                    email, password
                )
            ).also { result ->
                (result as? ResultSuccess)?.let {
                    _registerSuccessEventStream.emit(Unit)
                    saveToken(it.data.value)
                }
                (result as? ResultFail)?.let {
                    _errorStream.emit(it.error)
                }
            }
        }
    }

    private fun saveToken(token: String) {
        viewModelScope.launch {
            DataStoreProvider.instance.saveToken(token)
        }
    }

}