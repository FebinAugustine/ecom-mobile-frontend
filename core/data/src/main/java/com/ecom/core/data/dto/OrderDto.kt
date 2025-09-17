package com.ecom.core.data.dto

import com.ecom.core.domain.model.Order
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    @SerialName("_id")
    val id: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("seller_id")
    val sellerId: String,
    val products: List<String>, // Assuming a list of product IDs
    val total: Double,
    val deliveryAddress: String,
    @SerialName("payment_method")
    val paymentMethod: String,
    @SerialName("payment_status")
    val paymentStatus: String,
    val status: String,
    val createdAt: String // Assuming the API provides a timestamp
)

fun OrderDto.toDomain(): Order {
    return Order(
        id = this.id,
        total = this.total,
        status = this.status,
        date = this.createdAt // Using createdAt for the date
    )
}
