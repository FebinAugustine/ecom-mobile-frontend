package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ReviewRepository

class AddReviewUseCase(private val reviewRepository: ReviewRepository) {
    suspend operator fun invoke(productId: String, rating: Int, review: String) = reviewRepository.addReview(productId, rating, review)
}
