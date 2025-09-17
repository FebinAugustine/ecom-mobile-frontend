package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.SellerRepository

class RegisterSellerUseCase(private val sellerRepository: SellerRepository) {
    suspend operator fun invoke(
        fullname: String,
        email: String,
        password: String,
        phone: String,
        address: String,
        companyName: String,
        location: String
    ) = sellerRepository.register(fullname, email, password, phone, address, companyName, location)
}
