package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.SellerDto
import com.ecom.core.data.dto.SellerLoginRequest
import com.ecom.core.data.dto.SellerRegisterRequest

interface SellerAuthApiService {
    suspend fun login(request: SellerLoginRequest): SellerDto
    suspend fun register(request: SellerRegisterRequest): SellerDto
}
