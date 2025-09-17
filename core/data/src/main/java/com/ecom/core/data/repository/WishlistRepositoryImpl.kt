package com.ecom.core.data.repository

import com.ecom.core.data.dto.AddToWishlistRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.WishlistApiService
import com.ecom.core.domain.model.WishlistItem
import com.ecom.core.domain.repository.WishlistRepository

class WishlistRepositoryImpl(
    private val wishlistApiService: WishlistApiService,
    private val appPreferences: AppPreferences
) : WishlistRepository {

    override suspend fun getWishlist(): Result<List<WishlistItem>> {
        return try {
            val userId = appPreferences.getUserId()
            if (userId == null) return Result.failure(Exception("User not logged in"))

            val wishlistItems = wishlistApiService.getWishlist(userId).map { it.toDomain() }
            Result.success(wishlistItems)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToWishlist(productId: String): Result<Unit> {
        return try {
            val userId = appPreferences.getUserId()
            if (userId == null) return Result.failure(Exception("User not logged in"))

            val request = AddToWishlistRequest(userId, productId)
            wishlistApiService.addToWishlist(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeFromWishlist(wishlistItemId: String): Result<Unit> {
        return try {
            wishlistApiService.removeFromWishlist(wishlistItemId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
