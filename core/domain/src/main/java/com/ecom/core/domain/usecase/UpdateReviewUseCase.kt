package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ReviewRepository

class UpdateReviewUseCase(private val reviewRepository: ReviewRepository) {
    suspend operator fun invoke(reviewId: String, rating: Int, review: String) = reviewRepository.updateReview(reviewId, rating, review)
}
