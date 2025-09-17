package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ProductRepository

class GetProductsByCategoryUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(category: String) = productRepository.getProductsByCategory(category)
}
