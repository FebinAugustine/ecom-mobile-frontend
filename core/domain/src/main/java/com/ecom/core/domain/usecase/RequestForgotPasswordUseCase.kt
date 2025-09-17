package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.UserRepository

class RequestForgotPasswordUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String) = userRepository.forgotPassword(email)
}
