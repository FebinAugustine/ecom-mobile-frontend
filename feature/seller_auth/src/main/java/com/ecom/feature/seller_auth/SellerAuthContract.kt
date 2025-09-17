package com.ecom.feature.seller_auth

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

// --- Seller Login ---
data class SellerLoginState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = ""
) : ViewState

sealed class SellerLoginIntent : ViewIntent {
    data class EmailChanged(val email: String) : SellerLoginIntent()
    data class PasswordChanged(val password: String) : SellerLoginIntent()
    object LoginClicked : SellerLoginIntent()
}

sealed class SellerLoginEffect : ViewEffect {
    object NavigateToSellerDashboard : SellerLoginEffect()
    data class ShowError(val message: String) : SellerLoginEffect()
}

// --- Seller Signup ---
data class SellerSignupState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val fullname: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val address: String = "",
    val companyName: String = "",
    val location: String = ""
) : ViewState

sealed class SellerSignupIntent : ViewIntent {
    data class FullnameChanged(val fullname: String) : SellerSignupIntent()
    data class EmailChanged(val email: String) : SellerSignupIntent()
    data class PhoneChanged(val phone: String) : SellerSignupIntent()
    data class PasswordChanged(val password: String) : SellerSignupIntent()
    data class AddressChanged(val address: String) : SellerSignupIntent()
    data class CompanyNameChanged(val companyName: String) : SellerSignupIntent()
    data class LocationChanged(val location: String) : SellerSignupIntent()
    object SignupClicked : SellerSignupIntent()
}

sealed class SellerSignupEffect : ViewEffect {
    object NavigateToSellerDashboard : SellerSignupEffect()
    data class ShowError(val message: String) : SellerSignupEffect()
}
