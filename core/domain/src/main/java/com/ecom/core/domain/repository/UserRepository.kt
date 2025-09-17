package com.ecom.core.domain.repository

import com.ecom.core.domain.model.User

interface UserRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(fullname: String, email: String, password: String, phone: String): Result<User>
}
