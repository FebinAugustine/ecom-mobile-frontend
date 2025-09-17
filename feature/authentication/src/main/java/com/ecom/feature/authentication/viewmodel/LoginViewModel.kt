package com.ecom.feature.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.repository.UserRepository
import com.ecom.feature.authentication.LoginEffect
import com.ecom.feature.authentication.LoginIntent
import com.ecom.feature.authentication.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when (intent) {
                is LoginIntent.EmailChanged -> {
                    _state.value = _state.value.copy(email = intent.email)
                }
                is LoginIntent.PasswordChanged -> {
                    _state.value = _state.value.copy(password = intent.password)
                }
                LoginIntent.LoginClicked -> {
                    login()
                }
            }
        }
    }

    private suspend fun login() {
        _state.value = _state.value.copy(isLoading = true)
        userRepository.login(_state.value.email, _state.value.password)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(LoginEffect.NavigateToHome)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(LoginEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
