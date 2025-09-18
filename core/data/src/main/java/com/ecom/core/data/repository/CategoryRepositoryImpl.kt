package com.ecom.core.data.repository

import com.ecom.core.common.Failure
import com.ecom.core.data.dto.CreateCategoryRequest
import com.ecom.core.data.dto.UpdateCategoryRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.remote.service.CategoryApiService
import com.ecom.core.domain.model.Category
import com.ecom.core.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryApiService: CategoryApiService
) : CategoryRepository {

    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val categories = categoryApiService.getCategories().map { it.toDomain() }
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun createCategory(name: String, image: String): Result<Category> {
        return try {
            val request = CreateCategoryRequest(name, image)
            val createdCategory = categoryApiService.createCategory(request).toDomain()
            Result.success(createdCategory)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun updateCategory(categoryId: String, name: String, image: String): Result<Category> {
        return try {
            val request = UpdateCategoryRequest(name, image)
            val updatedCategory = categoryApiService.updateCategory(categoryId, request).toDomain()
            Result.success(updatedCategory)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun deleteCategory(categoryId: String): Result<Unit> {
        return try {
            categoryApiService.deleteCategory(categoryId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }
}
