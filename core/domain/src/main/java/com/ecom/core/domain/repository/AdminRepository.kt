package com.ecom.core.domain.repository

import com.ecom.core.domain.model.Admin

interface AdminRepository {
    suspend fun login(email: String, password: String): Result<Admin>
    suspend fun register(fullname: String, email: String, password: String, phone: String): Result<Admin>
}
