package com.ecom.feature.seller_auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.RegisterSellerUseCase
import com.ecom.feature.seller_auth.SellerSignupEffect
import com.ecom.feature.seller_auth.SellerSignupIntent
import com.ecom.feature.seller_auth.SellerSignupState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SellerSignupViewModel(
    private val registerSellerUseCase: RegisterSellerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SellerSignupState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SellerSignupEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: SellerSignupIntent) {
        viewModelScope.launch {
            when (intent) {
                is SellerSignupIntent.FullnameChanged -> _state.value = _state.value.copy(fullname = intent.fullname)
                is SellerSignupIntent.EmailChanged -> _state.value = _state.value.copy(email = intent.email)
                is SellerSignupIntent.PhoneChanged -> _state.value = _state.value.copy(phone = intent.phone)
                is SellerSignupIntent.PasswordChanged -> _state.value = _state.value.copy(password = intent.password)
                is SellerSignupIntent.AddressChanged -> _state.value = _state.value.copy(address = intent.address)
                is SellerSignupIntent.CompanyNameChanged -> _state.value = _state.value.copy(companyName = intent.companyName)
                is SellerSignupIntent.LocationChanged -> _state.value = _state.value.copy(location = intent.location)
                SellerSignupIntent.SignupClicked -> registerSeller()
            }
        }
    }

    private suspend fun registerSeller() {
        _state.value = _state.value.copy(isLoading = true)
        registerSellerUseCase(
            fullname = _state.value.fullname,
            email = _state.value.email,
            password = _state.value.password,
            phone = _state.value.phone,
            address = _state.value.address,
            companyName = _state.value.companyName,
            location = _state.value.location
        ).onSuccess {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(SellerSignupEffect.NavigateToSellerDashboard)
        }.onFailure {
            _state.value = _state.value.copy(isLoading = false)
            _effect.send(SellerSignupEffect.ShowError(it.message ?: "An unknown error occurred"))
        }
    }
}
