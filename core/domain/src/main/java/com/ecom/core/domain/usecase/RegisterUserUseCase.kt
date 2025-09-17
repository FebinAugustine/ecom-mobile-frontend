package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.UserRepository

class RegisterUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(fullname: String, email: String, password: String, phone: String) = userRepository.register(fullname, email, password, phone)
}
