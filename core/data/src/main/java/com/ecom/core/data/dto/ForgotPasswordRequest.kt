package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordRequest(
    val email: String
)
