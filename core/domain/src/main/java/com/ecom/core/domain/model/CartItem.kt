package com.ecom.core.domain.model

data class CartItem(
    val id: String,
    val productId: String,
    val quantity: Int
)
