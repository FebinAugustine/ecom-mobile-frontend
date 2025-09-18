package com.ecom.core.domain.repository

import com.ecom.core.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
    suspend fun createCategory(name: String, image: String): Result<Category>
    suspend fun updateCategory(categoryId: String, name: String, image: String): Result<Category>
    suspend fun deleteCategory(categoryId: String): Result<Unit>
}
