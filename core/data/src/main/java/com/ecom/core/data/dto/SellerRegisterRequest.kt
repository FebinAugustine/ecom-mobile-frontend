package com.ecom.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SellerRegisterRequest(
    val fullname: String,
    val email: String,
    val password: String,
    val phone: String,
    val address: String,
    @SerialName("company_name")
    val companyName: String,
    val location: String
)
