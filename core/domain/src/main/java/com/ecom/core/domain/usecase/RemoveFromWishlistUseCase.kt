package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.WishlistRepository

class RemoveFromWishlistUseCase(private val wishlistRepository: WishlistRepository) {
    suspend operator fun invoke(wishlistItemId: String) = wishlistRepository.removeFromWishlist(wishlistItemId)
}
