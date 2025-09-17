package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordVerifyCodeRequest(
    val email: String,
    val code: String,
    val password: String
)
