package com.ecom.core.domain.model

data class Review(
    val id: String,
    val userId: String,
    val productId: String,
    val rating: Int,
    val review: String
)
