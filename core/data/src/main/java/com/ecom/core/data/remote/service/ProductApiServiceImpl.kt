package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.ProductDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductApiServiceImpl(private val httpClient: HttpClient) : ProductApiService {

    override suspend fun getAllProducts(): List<ProductDto> {
        return httpClient.get("products").body()
    }

    override suspend fun getProductById(productId: String): ProductDto {
        return httpClient.get("products/$productId").body()
    }

    override suspend fun getProductsByCategory(category: String): List<ProductDto> {
        return httpClient.get("products/category/$category").body()
    }
}
