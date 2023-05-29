package com.sychev.bankclient.ui.screen.auth

import androidx.lifecycle.ViewModel
import com.sychev.shared.backend.models.base.ResultSuccess
import com.sychev.shared.domain.model.auth.RegistrationRequest
import com.sychev.shared.logger.logger
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

    fun registerUser(email: String, password: String) {
        CoroutineScope(Dispatchers.Default).launch {
            authRepository.registerUser(
                RegistrationRequest(
                    email, password
                )
            ).also { result ->
                if (result is ResultSuccess) {
                    _registerSuccessEventStream.emit(Unit)
                } else {
                    logger.logXertz("registrationError: $result")
                }
            }
        }
    }

}