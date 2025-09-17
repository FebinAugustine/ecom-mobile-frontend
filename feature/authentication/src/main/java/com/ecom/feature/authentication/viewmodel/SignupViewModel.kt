package com.ecom.feature.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.repository.UserRepository
import com.ecom.feature.authentication.SignupEffect
import com.ecom.feature.authentication.SignupIntent
import com.ecom.feature.authentication.SignupState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SignupEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: SignupIntent) {
        viewModelScope.launch {
            when (intent) {
                is SignupIntent.FullnameChanged -> {
                    _state.value = _state.value.copy(fullname = intent.fullname)
                }
                is SignupIntent.EmailChanged -> {
                    _state.value = _state.value.copy(email = intent.email)
                }
                is SignupIntent.PhoneChanged -> {
                    _state.value = _state.value.copy(phone = intent.phone)
                }
                is SignupIntent.PasswordChanged -> {
                    _state.value = _state.value.copy(password = intent.password)
                }
                SignupIntent.SignupClicked -> {
                    register()
                }
            }
        }
    }

    private suspend fun register() {
        _state.value = _state.value.copy(isLoading = true)
        userRepository.register(
            fullname = _state.value.fullname,
            email = _state.value.email,
            password = _state.value.password,
            phone = _state.value.phone
        )
        .onSuccess {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(SignupEffect.NavigateToHome)
        }
        .onFailure {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(SignupEffect.ShowError(it.message ?: "An unknown error occurred"))
        }
    }
}
