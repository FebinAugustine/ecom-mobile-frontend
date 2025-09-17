package com.ecom.feature.admin_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.RegisterAdminUseCase
import com.ecom.feature.admin_auth.AdminSignupEffect
import com.ecom.feature.admin_auth.AdminSignupIntent
import com.ecom.feature.admin_auth.AdminSignupState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AdminSignupViewModel(
    private val registerAdminUseCase: RegisterAdminUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AdminSignupState())
    val state = _state.asStateFlow()

    private val _effect = Channel<AdminSignupEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: AdminSignupIntent) {
        viewModelScope.launch {
            when (intent) {
                is AdminSignupIntent.FullnameChanged -> _state.value = _state.value.copy(fullname = intent.fullname)
                is AdminSignupIntent.EmailChanged -> _state.value = _state.value.copy(email = intent.email)
                is AdminSignupIntent.PhoneChanged -> _state.value = _state.value.copy(phone = intent.phone)
                is AdminSignupIntent.PasswordChanged -> _state.value = _state.value.copy(password = intent.password)
                AdminSignupIntent.SignupClicked -> registerAdmin()
            }
        }
    }

    private suspend fun registerAdmin() {
        _state.value = _state.value.copy(isLoading = true)
        registerAdminUseCase(
            fullname = _state.value.fullname,
            email = _state.value.email,
            password = _state.value.password,
            phone = _state.value.phone
        ).onSuccess {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(AdminSignupEffect.NavigateToAdminDashboard)
        }.onFailure {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(AdminSignupEffect.ShowError(it.message ?: "An unknown error occurred"))
        }
    }
}
