package com.ecom.feature.cart

import com.ecom.core.domain.model.CartItem

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

data class CartState(
    val isLoading: Boolean = false,
    val cartItems: List<CartItem> = emptyList(),
    val error: String? = null
) : ViewState

sealed class CartIntent : ViewIntent {
    object FetchCart : CartIntent()
    data class AddToCart(val productId: String, val quantity: Int) : CartIntent()
    data class RemoveFromCart(val cartItemId: String) : CartIntent()
}

sealed class CartEffect : ViewEffect {
    data class ShowError(val message: String) : CartEffect()
}
