package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.AdminRepository

class RegisterAdminUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(fullname: String, email: String, password: String, phone: String) = adminRepository.register(fullname, email, password, phone)
}
