package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.CategoryRepository

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke() = categoryRepository.getCategories()
}
