package com.ecom.feature.user_dashboard

import com.ecom.core.domain.model.User

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

data class UserProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
) : ViewState

sealed class UserProfileIntent : ViewIntent {
    object FetchUserProfile : UserProfileIntent()
}

sealed class UserProfileEffect : ViewEffect {
    data class ShowError(val message: String) : UserProfileEffect()
}
