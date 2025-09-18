package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val user: UserDto,
    val token: String,
    val refreshToken: String
)
