package com.ecom.core.domain.model

data class Reply(
    val id: String,
    val userId: String,
    val reviewId: String,
    val reply: String
)
