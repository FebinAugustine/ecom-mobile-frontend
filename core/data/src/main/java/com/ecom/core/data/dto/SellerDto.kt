package com.ecom.core.data.dto

import com.ecom.core.domain.model.Seller
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SellerDto(
    @SerialName("_id")
    val id: String,
    val fullname: String,
    val email: String,
    val phone: String,
    val address: String,
    @SerialName("company_name")
    val companyName: String,
    val location: String,
    val gst: String? = null,
    val pan: String? = null
)

fun SellerDto.toDomain(): Seller {
    return Seller(
        id = this.id,
        fullname = this.fullname,
        email = this.email,
        companyName = this.companyName
    )
}
