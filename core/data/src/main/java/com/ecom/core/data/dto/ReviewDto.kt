package com.ecom.core.data.dto

import com.ecom.core.domain.model.Review
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    @SerialName("_id")
    val id: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("product_id")
    val productId: String,
    val rating: Int,
    val review: String
)

fun ReviewDto.toDomain(): Review {
    return Review(
        id = this.id,
        userId = this.userId,
        productId = this.productId,
        rating = this.rating,
        review = this.review
    )
}
