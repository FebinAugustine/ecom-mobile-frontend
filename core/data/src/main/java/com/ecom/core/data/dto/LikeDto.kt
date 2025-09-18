package com.ecom.core.data.dto

import com.ecom.core.domain.model.Like
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LikeDto(
    @SerialName("_id")
    val id: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("product_id")
    val productId: String
)

fun LikeDto.toDomain(): Like {
    return Like(
        id = this.id,
        userId = this.userId,
        productId = this.productId
    )
}
