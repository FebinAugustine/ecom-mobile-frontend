package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.AdminRepository

class LoginAdminUseCase(private val adminRepository: AdminRepository) {
    suspend operator fun invoke(email: String, password: String) = adminRepository.login(email, password)
}
