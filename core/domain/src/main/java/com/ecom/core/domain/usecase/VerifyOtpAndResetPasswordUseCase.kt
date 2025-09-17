package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.UserRepository

class VerifyOtpAndResetPasswordUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, code: String, newPassword: String) = userRepository.verifyOtpAndResetPassword(email, code, newPassword)
}
