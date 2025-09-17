package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.CreateOrderRequest
import com.ecom.core.data.dto.OrderDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class OrderApiServiceImpl(private val httpClient: HttpClient) : OrderApiService {

    override suspend fun getUserOrders(userId: String): List<OrderDto> {
        // Note: The API endpoint in ApiEndPoints.md is just "/", which is ambiguous.
        // Assuming it should be something like "/orders/user/{userId}" for a real app.
        return httpClient.get("orders/user/$userId").body()
    }

    override suspend fun createOrder(request: CreateOrderRequest): OrderDto {
        return httpClient.post("orders") {
            setBody(request)
        }.body()
    }
}
