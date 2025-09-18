package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDto<T>(
    val statusCode: Int,
    val data: T,
    val message: String,
    val success: Boolean
)
