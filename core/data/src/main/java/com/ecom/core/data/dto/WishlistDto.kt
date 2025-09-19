package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class WishlistDto(
    val _id: String,
    val user: String,
    val products: List<WishlistItemDto>
)
