package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.LoginRequest
import com.ecom.core.data.dto.LoginResponse
import com.ecom.core.data.dto.RegisterRequest

interface AuthApiService {
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun register(request: RegisterRequest): LoginResponse
}
