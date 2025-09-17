package com.ecom.feature.seller_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.LoginSellerUseCase
import com.ecom.feature.seller_auth.SellerLoginEffect
import com.ecom.feature.seller_auth.SellerLoginIntent
import com.ecom.feature.seller_auth.SellerLoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SellerLoginViewModel(
    private val loginSellerUseCase: LoginSellerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SellerLoginState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SellerLoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: SellerLoginIntent) {
        viewModelScope.launch {
            when (intent) {
                is SellerLoginIntent.EmailChanged -> {
                    _state.value = _state.value.copy(email = intent.email)
                }
                is SellerLoginIntent.PasswordChanged -> {
                    _state.value = _state.value.copy(password = intent.password)
                }
                SellerLoginIntent.LoginClicked -> {
                    login()
                }
            }
        }
    }

    private suspend fun login() {
        _state.value = _state.value.copy(isLoading = true)
        loginSellerUseCase(state.value.email, state.value.password)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(SellerLoginEffect.NavigateToSellerDashboard)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(SellerLoginEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
