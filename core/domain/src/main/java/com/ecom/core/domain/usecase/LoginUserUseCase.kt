package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.UserRepository

class LoginUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String) = userRepository.login(email, password)
}
