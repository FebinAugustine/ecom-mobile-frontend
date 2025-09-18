package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.CategoryDto
import com.ecom.core.data.dto.CreateCategoryRequest
import com.ecom.core.data.dto.UpdateCategoryRequest

interface CategoryApiService {
    suspend fun getCategories(): List<CategoryDto>
    suspend fun createCategory(request: CreateCategoryRequest): CategoryDto
    suspend fun updateCategory(categoryId: String, request: UpdateCategoryRequest): CategoryDto
    suspend fun deleteCategory(categoryId: String)
}
