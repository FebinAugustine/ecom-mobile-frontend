package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.CartRepository

class GetCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke() = cartRepository.getCart()
}
