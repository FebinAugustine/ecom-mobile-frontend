package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.AddToWishlistRequest
import com.ecom.core.data.dto.WishlistItemDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class WishlistApiServiceImpl(private val httpClient: HttpClient) : WishlistApiService {

    override suspend fun getWishlist(userId: String): List<WishlistItemDto> {
        // Note: The API endpoint in ApiEndPoints.md is just "/", which is ambiguous.
        // Assuming it should be something like "/wishlists/{userId}" for a real app.
        // For now, I'll use a placeholder that aligns with the general REST pattern.
        return httpClient.get("wishlists/$userId").body()
    }

    override suspend fun addToWishlist(request: AddToWishlistRequest) {
        httpClient.post("wishlists") {
            setBody(request)
        }
    }

    override suspend fun removeFromWishlist(wishlistItemId: String) {
        httpClient.delete("wishlists/$wishlistItemId")
    }
}
