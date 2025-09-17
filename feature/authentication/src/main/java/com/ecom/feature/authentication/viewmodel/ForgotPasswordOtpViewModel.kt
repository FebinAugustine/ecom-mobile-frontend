package com.ecom.feature.authentication.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.repository.UserRepository
import com.ecom.feature.authentication.ForgotPasswordOtpEffect
import com.ecom.feature.authentication.ForgotPasswordOtpIntent
import com.ecom.feature.authentication.ForgotPasswordOtpState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ForgotPasswordOtpViewModel(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordOtpState())
    val state = _state.asStateFlow()

    private val _effect = Channel<ForgotPasswordOtpEffect>()
    val effect = _effect.receiveAsFlow()

    private val email: String = savedStateHandle.get<String>("email") ?: ""

    fun onIntent(intent: ForgotPasswordOtpIntent) {
        viewModelScope.launch {
            when (intent) {
                is ForgotPasswordOtpIntent.OtpChanged -> {
                    _state.value = _state.value.copy(otp = intent.otp)
                }
                is ForgotPasswordOtpIntent.NewPasswordChanged -> {
                    _state.value = _state.value.copy(newPassword = intent.newPassword)
                }
                ForgotPasswordOtpIntent.SubmitClicked -> {
                    submit()
                }
            }
        }
    }

    private suspend fun submit() {
        _state.value = _state.value.copy(isLoading = true)
        userRepository.verifyOtpAndResetPassword(
            email = email,
            code = _state.value.otp,
            newPassword = _state.value.newPassword
        )
        .onSuccess {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(ForgotPasswordOtpEffect.NavigateToLogin)
        }
        .onFailure {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(ForgotPasswordOtpEffect.ShowError(it.message ?: "An unknown error occurred"))
        }
    }
}
