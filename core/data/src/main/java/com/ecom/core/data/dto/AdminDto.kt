package com.ecom.core.data.dto

import com.ecom.core.domain.model.Admin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdminDto(
    @SerialName("_id")
    val id: String,
    val fullname: String,
    val email: String,
    val phone: String
)

fun AdminDto.toDomain(): Admin {
    return Admin(
        id = this.id,
        fullname = this.fullname,
        email = this.email
    )
}
