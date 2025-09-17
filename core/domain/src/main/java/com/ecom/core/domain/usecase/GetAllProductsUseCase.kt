package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ProductRepository

class GetAllProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke() = productRepository.getAllProducts()
}
