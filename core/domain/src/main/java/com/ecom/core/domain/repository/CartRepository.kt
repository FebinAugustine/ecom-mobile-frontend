package com.ecom.core.domain.repository

import com.ecom.core.domain.model.CartItem

interface CartRepository {
    suspend fun getCart(): Result<List<CartItem>>
    suspend fun addToCart(productId: String, quantity: Int): Result<Unit>
    suspend fun removeFromCart(cartItemId: String): Result<Unit>
}
