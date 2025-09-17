package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SellerLoginRequest(
    val email: String,
    val password: String
)
