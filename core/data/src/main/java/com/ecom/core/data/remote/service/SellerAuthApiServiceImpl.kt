package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.SellerDto
import com.ecom.core.data.dto.SellerLoginRequest
import com.ecom.core.data.dto.SellerRegisterRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class SellerAuthApiServiceImpl(private val httpClient: HttpClient) : SellerAuthApiService {

    override suspend fun login(request: SellerLoginRequest): SellerDto {
        return httpClient.post("sellers/login") {
            setBody(request)
        }.body()
    }

    override suspend fun register(request: SellerRegisterRequest): SellerDto {
        return httpClient.post("sellers/register") {
            setBody(request)
        }.body()
    }
}
