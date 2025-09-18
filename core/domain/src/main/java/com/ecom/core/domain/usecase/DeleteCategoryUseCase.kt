package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.CategoryRepository

class DeleteCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryId: String) = categoryRepository.deleteCategory(categoryId)
}
