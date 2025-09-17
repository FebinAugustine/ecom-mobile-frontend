package com.ecom.core.data.dto

import com.ecom.core.domain.model.WishlistItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WishlistItemDto(
    @SerialName("_id")
    val id: String,
    @SerialName("product_id")
    val productId: String
)

fun WishlistItemDto.toDomain(): WishlistItem {
    return WishlistItem(
        id = this.id,
        productId = this.productId
    )
}
