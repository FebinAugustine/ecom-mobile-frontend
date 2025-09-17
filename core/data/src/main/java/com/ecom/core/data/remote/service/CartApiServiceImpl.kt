package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.AddToCartRequest
import com.ecom.core.data.dto.CartItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class CartApiServiceImpl(private val httpClient: HttpClient) : CartApiService {

    override suspend fun getCart(userId: String): List<CartItemDto> {
        // Note: The API endpoint in ApiEndPoints.md is just "/", which is ambiguous.
        // Assuming it should be something like "/carts/{userId}" for a real app.
        // For now, I'll use a placeholder that aligns with the general REST pattern.
        return httpClient.get("carts/$userId").body()
    }

    override suspend fun addToCart(request: AddToCartRequest) {
        httpClient.post("carts") {
            setBody(request)
        }
    }

    override suspend fun removeFromCart(cartItemId: String) {
        httpClient.delete("carts/$cartItemId")
    }
}
