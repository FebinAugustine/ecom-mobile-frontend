package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCategoryRequest(
    val name: String,
    val image: String
)
