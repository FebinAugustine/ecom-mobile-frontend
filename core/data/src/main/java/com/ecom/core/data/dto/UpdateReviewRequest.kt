package com.ecom.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateReviewRequest(
    val rating: Int,
    val review: String
)
