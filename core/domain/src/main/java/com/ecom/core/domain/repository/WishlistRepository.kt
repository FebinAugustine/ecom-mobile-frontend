package com.ecom.core.domain.repository

import com.ecom.core.domain.model.WishlistItem

interface WishlistRepository {
    suspend fun getWishlist(): Result<List<WishlistItem>>
    suspend fun addToWishlist(productId: String): Result<Unit>
    suspend fun removeFromWishlist(wishlistItemId: String): Result<Unit>
}
