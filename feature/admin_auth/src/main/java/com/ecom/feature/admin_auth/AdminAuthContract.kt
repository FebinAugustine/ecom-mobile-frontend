package com.ecom.feature.admin_auth

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

// --- Admin Login ---
data class AdminLoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = ""
) : ViewState

sealed class AdminLoginIntent : ViewIntent {
    data class EmailChanged(val email: String) : AdminLoginIntent()
    data class PasswordChanged(val password: String) : AdminLoginIntent()
    object LoginClicked : AdminLoginIntent()
}

sealed class AdminLoginEffect : ViewEffect {
    object NavigateToAdminDashboard : AdminLoginEffect()
    data class ShowError(val message: String) : AdminLoginEffect()
}

// --- Admin Signup ---
data class AdminSignupState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val fullname: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = ""
) : ViewState

sealed class AdminSignupIntent : ViewIntent {
    data class FullnameChanged(val fullname: String) : AdminSignupIntent()
    data class EmailChanged(val email: String) : AdminSignupIntent()
    data class PhoneChanged(val phone: String) : AdminSignupIntent()
    data class PasswordChanged(val password: String) : AdminSignupIntent()
    object SignupClicked : AdminSignupIntent()
}

sealed class AdminSignupEffect : ViewEffect {
    object NavigateToAdminDashboard : AdminSignupEffect()
    data class ShowError(val message: String) : AdminSignupEffect()
}
