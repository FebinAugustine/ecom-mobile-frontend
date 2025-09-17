package com.ecom.core.data.repository

import com.ecom.core.data.dto.AddToCartRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.CartApiService
import com.ecom.core.domain.model.CartItem
import com.ecom.core.domain.repository.CartRepository

class CartRepositoryImpl(
    private val cartApiService: CartApiService,
    private val appPreferences: AppPreferences
) : CartRepository {

    override suspend fun getCart(): Result<List<CartItem>> {
        return try {
            val userId = appPreferences.getUserId()
            if (userId == null) return Result.failure(Exception("User not logged in"))

            val cartItems = cartApiService.getCart(userId).map { it.toDomain() }
            Result.success(cartItems)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToCart(productId: String, quantity: Int): Result<Unit> {
        return try {
            val userId = appPreferences.getUserId()
            if (userId == null) return Result.failure(Exception("User not logged in"))

            val request = AddToCartRequest(userId, productId, quantity)
            cartApiService.addToCart(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeFromCart(cartItemId: String): Result<Unit> {
        return try {
            cartApiService.removeFromCart(cartItemId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
