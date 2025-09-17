package com.ecom.core.data.dto

import com.ecom.core.domain.model.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("_id")
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val discount: Int,
    val inStock: Int,
    val color: String,
    val sizes: List<String>,
    val image: String,
    val category: String
)

fun ProductDto.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        imageUrl = this.image
    )
}
