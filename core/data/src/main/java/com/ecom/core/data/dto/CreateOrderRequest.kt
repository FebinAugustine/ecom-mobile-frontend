package com.ecom.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequest(
    @SerialName("user_id")
    val userId: String,
    @SerialName("seller_id")
    val sellerId: String,
    val products: List<String>,
    val total: Double,
    val deliveryAddress: String,
    @SerialName("payment_method")
    val paymentMethod: String,
    @SerialName("payment_status")
    val paymentStatus: String,
    val status: String
)
