package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.CategoryDto
import com.ecom.core.data.dto.CreateCategoryRequest
import com.ecom.core.data.dto.UpdateCategoryRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class CategoryApiServiceImpl(private val httpClient: HttpClient) : CategoryApiService {

    override suspend fun getCategories(): List<CategoryDto> {
        return httpClient.get("categories").body()
    }

    override suspend fun createCategory(request: CreateCategoryRequest): CategoryDto {
        return httpClient.post("categories") {
            setBody(request)
        }.body()
    }

    override suspend fun updateCategory(categoryId: String, request: UpdateCategoryRequest): CategoryDto {
        return httpClient.put("categories/$categoryId") {
            setBody(request)
        }.body()
    }

    override suspend fun deleteCategory(categoryId: String) {
        httpClient.delete("categories/$categoryId")
    }
}
