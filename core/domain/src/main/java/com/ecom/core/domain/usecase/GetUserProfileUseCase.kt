package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.UserRepository

class GetUserProfileUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getUserProfile()
}
