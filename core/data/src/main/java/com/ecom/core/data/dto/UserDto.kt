package com.ecom.core.data.dto

import com.ecom.core.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("_id")
    val id: String,
    val fullname: String,
    val email: String
)

fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        fullname = this.fullname,
        email = this.email
    )
}
