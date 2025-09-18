package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.CategoryRepository

class UpdateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryId: String, name: String, image: String) = categoryRepository.updateCategory(categoryId, name, image)
}
