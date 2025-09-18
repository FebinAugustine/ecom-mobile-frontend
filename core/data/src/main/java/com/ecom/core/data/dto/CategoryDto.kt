package com.ecom.core.data.dto

import com.ecom.core.domain.model.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("_id")
    val id: String,
    val name: String,
    val image: String
)

fun CategoryDto.toDomain(): Category {
    return Category(
        id = this.id,
        name = this.name,
        image = this.image
    )
}
