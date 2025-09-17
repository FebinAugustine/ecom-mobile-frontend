package com.ecom.core.domain.model

data class Order(
    val id: String,
    val total: Double,
    val status: String,
    val date: String // Assuming we'll format the date as a string for display
)
