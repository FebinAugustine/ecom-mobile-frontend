package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.WishlistRepository

class GetWishlistUseCase(private val wishlistRepository: WishlistRepository) {
    suspend operator fun invoke() = wishlistRepository.getWishlist()
}
