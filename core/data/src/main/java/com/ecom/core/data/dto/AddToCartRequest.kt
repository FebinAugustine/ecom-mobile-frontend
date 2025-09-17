package com.ecom.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    @SerialName("user_id")
    val userId: String,
    @SerialName("product_id")
    val productId: String,
    val quantity: Int
)
