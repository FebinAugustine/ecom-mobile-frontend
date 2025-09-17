package com.ecom.core.data.repository

import com.ecom.core.data.dto.SellerLoginRequest
import com.ecom.core.data.dto.SellerRegisterRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.SellerAuthApiService
import com.ecom.core.domain.model.Seller
import com.ecom.core.domain.repository.SellerRepository

class SellerRepositoryImpl(
    private val sellerAuthApiService: SellerAuthApiService,
    private val appPreferences: AppPreferences
) : SellerRepository {

    override suspend fun login(email: String, password: String): Result<Seller> {
        return try {
            val request = SellerLoginRequest(email, password)
            val response = sellerAuthApiService.login(request)
            // Assuming we save seller auth info similarly to user auth info
            appPreferences.saveAuthInfo(response.id, response.id) // Placeholder for token
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        fullname: String,
        email: String,
        password: String,
        phone: String,
        address: String,
        companyName: String,
        location: String
    ): Result<Seller> {
        return try {
            val request = SellerRegisterRequest(fullname, email, password, phone, address, companyName, location)
            val response = sellerAuthApiService.register(request)
            appPreferences.saveAuthInfo(response.id, response.id) // Placeholder for token
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
