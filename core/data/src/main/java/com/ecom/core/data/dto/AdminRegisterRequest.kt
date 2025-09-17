package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdminRegisterRequest(
    val fullname: String,
    val email: String,
    val password: String,
    val phone: String
)
