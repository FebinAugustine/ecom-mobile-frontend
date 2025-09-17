package com.ecom.feature.wishlist

import com.ecom.core.domain.model.WishlistItem

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

data class WishlistState(
    val isLoading: Boolean = false,
    val wishlistItems: List<WishlistItem> = emptyList(),
    val error: String? = null
) : ViewState

sealed class WishlistIntent : ViewIntent {
    object FetchWishlist : WishlistIntent()
    data class AddToWishlist(val productId: String) : WishlistIntent()
    data class RemoveFromWishlist(val wishlistItemId: String) : WishlistIntent()
}

sealed class WishlistEffect : ViewEffect {
    data class ShowError(val message: String) : WishlistEffect()
}
