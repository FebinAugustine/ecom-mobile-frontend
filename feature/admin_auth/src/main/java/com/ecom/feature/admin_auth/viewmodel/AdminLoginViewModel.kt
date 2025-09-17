package com.ecom.feature.admin_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.LoginAdminUseCase
import com.ecom.feature.admin_auth.AdminLoginEffect
import com.ecom.feature.admin_auth.AdminLoginIntent
import com.ecom.feature.admin_auth.AdminLoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AdminLoginViewModel(
    private val loginAdminUseCase: LoginAdminUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AdminLoginState())
    val state = _state.asStateFlow()

    private val _effect = Channel<AdminLoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: AdminLoginIntent) {
        viewModelScope.launch {
            when (intent) {
                is AdminLoginIntent.EmailChanged -> {
                    _state.value = _state.value.copy(email = intent.email)
                }
                is AdminLoginIntent.PasswordChanged -> {
                    _state.value = _state.value.copy(password = intent.password)
                }
                AdminLoginIntent.LoginClicked -> {
                    login()
                }
            }
        }
    }

    private suspend fun login() {
        _state.value = _state.value.copy(isLoading = true)
        loginAdminUseCase(state.value.email, state.value.password)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(AdminLoginEffect.NavigateToAdminDashboard)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(AdminLoginEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
