package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val products: List<ProductDto>,
    val totalProducts: Int,
    val page: Int,
    val totalPages: Int
)
