package com.ecom.core.data.repository

import com.ecom.core.data.dto.AdminLoginRequest
import com.ecom.core.data.dto.AdminRegisterRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.AdminAuthApiService
import com.ecom.core.domain.model.Admin
import com.ecom.core.domain.repository.AdminRepository

class AdminRepositoryImpl(
    private val adminAuthApiService: AdminAuthApiService,
    private val appPreferences: AppPreferences
) : AdminRepository {

    override suspend fun login(email: String, password: String): Result<Admin> {
        return try {
            val request = AdminLoginRequest(email, password)
            val response = adminAuthApiService.login(request)
            // Assuming we save admin auth info similarly to user auth info
            appPreferences.saveAuthInfo(response.id, response.id) // Placeholder for token
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(fullname: String, email: String, password: String, phone: String): Result<Admin> {
        return try {
            val request = AdminRegisterRequest(fullname, email, password, phone)
            val response = adminAuthApiService.register(request)
            appPreferences.saveAuthInfo(response.id, response.id) // Placeholder for token
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
