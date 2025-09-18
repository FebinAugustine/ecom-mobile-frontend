package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateCategoryRequest(
    val name: String,
    val image: String
)
