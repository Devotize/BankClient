package com.sychev.bankclient.ui.screen.auth

import androidx.lifecycle.ViewModel
import com.sychev.shared.backend.models.base.ResultFail
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.backend.models.errors.RequestError
import com.sychev.shared.domain.model.auth.LoginRequest
import com.sychev.shared.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _loginSuccessEventStream = MutableSharedFlow<Unit>()
    val loginSuccessEventStream = _loginSuccessEventStream.asSharedFlow()

    private val _errorStream = MutableSharedFlow<RequestError>()
    val errorStream = _errorStream.asSharedFlow()

    fun loginUser(email: String, password: String) {
        CoroutineScope(Dispatchers.Default).launch {
            authRepository.loginUser(
                LoginRequest(
                    email, password
                )
            ).also { result ->
                (result as? ResultSuccess)?.let {
                    _loginSuccessEventStream.emit(Unit)
                }
                (result as? ResultFail)?.let {
                    _errorStream.emit(it.error)
                }
            }
        }
    }


}