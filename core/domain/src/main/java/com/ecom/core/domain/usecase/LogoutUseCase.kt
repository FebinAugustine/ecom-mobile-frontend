package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.UserRepository

class LogoutUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.logout()
}
