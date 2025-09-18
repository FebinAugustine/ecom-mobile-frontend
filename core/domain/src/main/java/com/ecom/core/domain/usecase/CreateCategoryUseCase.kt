package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.CategoryRepository

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(name: String, image: String) = categoryRepository.createCategory(name, image)
}
