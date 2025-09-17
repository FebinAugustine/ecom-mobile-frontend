package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.CartRepository

class AddToCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(productId: String, quantity: Int) = cartRepository.addToCart(productId, quantity)
}
