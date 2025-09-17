package com.ecom.feature.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.repository.UserRepository
import com.ecom.feature.authentication.ForgotPasswordEffect
import com.ecom.feature.authentication.ForgotPasswordIntent
import com.ecom.feature.authentication.ForgotPasswordState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordState())
    val state = _state.asStateFlow()

    private val _effect = Channel<ForgotPasswordEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: ForgotPasswordIntent) {
        viewModelScope.launch {
            when (intent) {
                is ForgotPasswordIntent.EmailChanged -> {
                    _state.value = _state.value.copy(email = intent.email)
                }
                ForgotPasswordIntent.SubmitClicked -> {
                    submit()
                }
            }
        }
    }

    private suspend fun submit() {
        _state.value = _state.value.copy(isLoading = true)
        userRepository.forgotPassword(_state.value.email)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(ForgotPasswordEffect.NavigateToOtpVerification)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(ForgotPasswordEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
