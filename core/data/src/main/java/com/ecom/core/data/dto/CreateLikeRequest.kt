package com.ecom.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateLikeRequest(
    @SerialName("user_id")
    val userId: String,
    @SerialName("product_id")
    val productId: String
)
