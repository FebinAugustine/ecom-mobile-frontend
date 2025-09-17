package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.CartRepository

class RemoveFromCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(cartItemId: String) = cartRepository.removeFromCart(cartItemId)
}
