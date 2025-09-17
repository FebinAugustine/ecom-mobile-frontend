package com.ecom.feature.user_dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.GetUserProfileUseCase
import com.ecom.feature.user_dashboard.UserProfileEffect
import com.ecom.feature.user_dashboard.UserProfileIntent
import com.ecom.feature.user_dashboard.UserProfileState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserProfileViewModel(private val getUserProfileUseCase: GetUserProfileUseCase) : ViewModel() {

    private val _state = MutableStateFlow(UserProfileState())
    val state = _state.asStateFlow()

    private val _effect = Channel<UserProfileEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(UserProfileIntent.FetchUserProfile)
    }

    fun onIntent(intent: UserProfileIntent) {
        viewModelScope.launch {
            when (intent) {
                UserProfileIntent.FetchUserProfile -> {
                    fetchUserProfile()
                }
            }
        }
    }

    private suspend fun fetchUserProfile() {
        _state.value = _state.value.copy(isLoading = true)
        getUserProfileUseCase()
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, user = it)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(UserProfileEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
