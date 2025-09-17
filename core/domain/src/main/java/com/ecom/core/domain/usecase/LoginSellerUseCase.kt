package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.SellerRepository

class LoginSellerUseCase(private val sellerRepository: SellerRepository) {
    suspend operator fun invoke(email: String, password: String) = sellerRepository.login(email, password)
}
