package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.AdminDto
import com.ecom.core.data.dto.AdminLoginRequest
import com.ecom.core.data.dto.AdminRegisterRequest

interface AdminAuthApiService {
    suspend fun login(request: AdminLoginRequest): AdminDto
    suspend fun register(request: AdminRegisterRequest): AdminDto
}
