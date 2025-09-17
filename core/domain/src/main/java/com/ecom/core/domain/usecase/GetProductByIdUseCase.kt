package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ProductRepository

class GetProductByIdUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productId: String) = productRepository.getProductById(productId)
}
