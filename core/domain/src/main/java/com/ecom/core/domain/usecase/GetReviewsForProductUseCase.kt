package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ReviewRepository

class GetReviewsForProductUseCase(private val reviewRepository: ReviewRepository) {
    suspend operator fun invoke(productId: String) = reviewRepository.getReviewsForProduct(productId)
}
