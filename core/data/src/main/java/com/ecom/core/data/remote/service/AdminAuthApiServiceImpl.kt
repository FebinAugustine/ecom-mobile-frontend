package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.AdminDto
import com.ecom.core.data.dto.AdminLoginRequest
import com.ecom.core.data.dto.AdminRegisterRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AdminAuthApiServiceImpl(private val httpClient: HttpClient) : AdminAuthApiService {

    override suspend fun login(request: AdminLoginRequest): AdminDto {
        return httpClient.post("admins/login") {
            setBody(request)
        }.body()
    }

    override suspend fun register(request: AdminRegisterRequest): AdminDto {
        return httpClient.post("admins/register") {
            setBody(request)
        }.body()
    }
}
