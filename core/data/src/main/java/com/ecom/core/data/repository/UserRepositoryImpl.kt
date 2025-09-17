package com.ecom.core.data.repository

import com.ecom.core.data.dto.LoginRequest
import com.ecom.core.data.dto.RegisterRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.AuthApiService
import com.ecom.core.domain.model.User
import com.ecom.core.domain.repository.UserRepository

class UserRepositoryImpl(
    private val authApiService: AuthApiService,
    private val appPreferences: AppPreferences
) : UserRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val request = LoginRequest(email, password)
            val response = authApiService.login(request)
            appPreferences.saveToken(response.token)
            Result.success(response.user.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        fullname: String,
        email: String,
        password: String,
        phone: String
    ): Result<User> {
        return try {
            val request = RegisterRequest(fullname, email, password, phone)
            val response = authApiService.register(request)
            appPreferences.saveToken(response.token)
            Result.success(response.user.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
