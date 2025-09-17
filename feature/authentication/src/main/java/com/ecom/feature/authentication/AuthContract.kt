package com.ecom.feature.authentication

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

// --- Login ---
data class LoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = ""
) : ViewState

sealed class LoginIntent : ViewIntent {
    data class EmailChanged(val email: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    object LoginClicked : LoginIntent()
}

sealed class LoginEffect : ViewEffect {
    object NavigateToHome : LoginEffect()
    object NavigateToForgotPassword : LoginEffect()
    object NavigateToSignUp : LoginEffect()
    data class ShowError(val message: String) : LoginEffect()
}


// --- Signup ---
data class SignupState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val fullname: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = ""
) : ViewState

sealed class SignupIntent : ViewIntent {
    data class FullnameChanged(val fullname: String) : SignupIntent()
    data class EmailChanged(val email: String) : SignupIntent()
    data class PhoneChanged(val phone: String) : SignupIntent()
    data class PasswordChanged(val password: String) : SignupIntent()
    object SignupClicked : SignupIntent()
}

sealed class SignupEffect : ViewEffect {
    object NavigateToHome : SignupEffect()
    data class ShowError(val message: String) : SignupEffect()
}


// --- Forgot Password ---
data class ForgotPasswordState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String = ""
) : ViewState

sealed class ForgotPasswordIntent : ViewIntent {
    data class EmailChanged(val email: String) : ForgotPasswordIntent()
    object SubmitClicked : ForgotPasswordIntent()
}

sealed class ForgotPasswordEffect : ViewEffect {
    object NavigateToOtpVerification : ForgotPasswordEffect()
    data class ShowError(val message: String) : ForgotPasswordEffect()
}

// --- Forgot Password OTP ---
data class ForgotPasswordOtpState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val otp: String = "",
    val newPassword: String = ""
) : ViewState

sealed class ForgotPasswordOtpIntent : ViewIntent {
    data class OtpChanged(val otp: String) : ForgotPasswordOtpIntent()
    data class NewPasswordChanged(val newPassword: String) : ForgotPasswordOtpIntent()
    object SubmitClicked : ForgotPasswordOtpIntent()
}

sealed class ForgotPasswordOtpEffect : ViewEffect {
    object NavigateToLogin : ForgotPasswordOtpEffect()
    data class ShowError(val message: String) : ForgotPasswordOtpEffect()
}
