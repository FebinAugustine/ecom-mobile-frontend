package com.ecom.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateReplyRequest(
    @SerialName("user_id")
    val userId: String,
    @SerialName("comment_id") // As per ApiEndPoints.md
    val reviewId: String,
    val reply: String
)
