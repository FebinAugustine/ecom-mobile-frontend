package com.ecom.core.domain.repository

import com.ecom.core.domain.model.Seller

interface SellerRepository {
    suspend fun login(email: String, password: String): Result<Seller>
    suspend fun register(
        fullname: String,
        email: String,
        password: String,
        phone: String,
        address: String,
        companyName: String,
        location: String
    ): Result<Seller>
}
