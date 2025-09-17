package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.ForgotPasswordRequest
import com.ecom.core.data.dto.ForgotPasswordVerifyCodeRequest
import com.ecom.core.data.dto.LoginRequest
import com.ecom.core.data.dto.LoginResponse
import com.ecom.core.data.dto.RegisterRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class AuthApiServiceImpl(private val httpClient: HttpClient) : AuthApiService {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return httpClient.post("users/login") {
            setBody(request)
        }.body()
    }

    override suspend fun register(request: RegisterRequest): LoginResponse {
        return httpClient.post("users/register") {
            setBody(request)
        }.body()
    }

    override suspend fun forgotPassword(request: ForgotPasswordRequest) {
        httpClient.post("users/forgot-password") {
            setBody(request)
        }
    }

    override suspend fun forgotPasswordVerifyCode(request: ForgotPasswordVerifyCodeRequest) {
        httpClient.post("users/forgot-password-verify-code") {
            setBody(request)
        }
    }
}
