package com.ecom.core.data.dto

import com.ecom.core.domain.model.CartItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemDto(
    @SerialName("_id")
    val id: String,
    @SerialName("product_id")
    val productId: String,
    val quantity: Int
)

fun CartItemDto.toDomain(): CartItem {
    return CartItem(
        id = this.id,
        productId = this.productId,
        quantity = this.quantity
    )
}
